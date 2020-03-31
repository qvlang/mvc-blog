package com.lang.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lang.blog.centers.PublishArticle;
import com.lang.blog.centers.SubscriptCenter;
import com.lang.blog.mapper.IArticleLogMapper;
import com.lang.blog.mapper.IArticleMapper;
import com.lang.blog.model.Article;
import com.lang.blog.model.ArticleLog;
import com.lang.blog.model.result.CommonPageInfo;
import com.lang.blog.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 前提：A方法调用B方法时，B方法有多个修改SQL
 * <p>
 * A方法没开启事务，B方法开启事务：A和B在同一类中，事务无效；A和B不在同一类中，事务生效。
 * <p>
 * A方法开启事务，B方法没开启事务：A和B在同一类中，事务生效；A和B不在同一类中，事务生效。
 * 解决方法： 手动获取代理类AopContext.currentProxy() 通过代理类调用使得事务生效
 */

@Service
@Slf4j
public class ArticleServiceImpl implements IArticleService {
    //redis中的键值
    private final String REDIS_ARTICLES_KEY = "articles";
    private final String REDIS_ARTICLE_KEY = "article";
    @Autowired
    private IArticleMapper mapper;
    //引入日志记录mapper
    @Autowired
    private IArticleLogMapper logMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SubscriptCenter subscriptCenter;

    public Article getArticleByid(Long id) {
        log.info(id.toString());
        //判断redis缓存中是否有数据 有的话直接读缓存 不请求数据库
        String articleCache = stringRedisTemplate.opsForValue().get(REDIS_ARTICLE_KEY + id);
        if (!StringUtils.isEmpty(articleCache)) {
            return JSON.parseObject(articleCache, Article.class);
        }
        Article article = mapper.findArticleByid(id);
        String articleString = JSON.toJSONString(article);
        //设置缓存过期时间为30S
        stringRedisTemplate.opsForValue().set(REDIS_ARTICLE_KEY + id, articleString, 30, TimeUnit.SECONDS);
        return article;
    }

    /**
     * @Description: 批量id批量获取文章
     * @Param:
     * @return:
     */
    public List<Article> getArticleBatchIds(List<Long> ids) {
        return mapper.findAllBatchByids(ids);
    }

    //新增文章后记录日志到数据库
    //使用事务保证两者必须同时完成
    //@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public boolean createArticle(Article article) {
        int affectrow = mapper.insertArticle(article);
        //向缓存的右边加入
        String s = JSON.toJSONString(article);
        stringRedisTemplate.opsForList().rightPush(REDIS_ARTICLES_KEY, s);
        //为列表集合设置缓存过期时间
        stringRedisTemplate.expire(REDIS_ARTICLES_KEY, 3, TimeUnit.MINUTES);
        //增加操作日志
        ArticleServiceImpl currentProxy = (ArticleServiceImpl) AopContext.currentProxy();
        currentProxy.writeArticleLog("新增文章", article);
        //int k = 10 / 0;
        //发布文章后 关注他的用户就能收到文章推送
        PublishArticle publishArticle = new PublishArticle();
        subscriptCenter.publish(article.getAuthorId(), article.getId(), publishArticle);
        return affectrow > 0;
    }

    public boolean updateArticle(Article article) {
        int affectRow = mapper.updateByid(article);
        //更新缓存
        if (stringRedisTemplate.hasKey(REDIS_ARTICLES_KEY)) {
            List<String> list = stringRedisTemplate.opsForList().range(REDIS_ARTICLES_KEY, 0, -1);
            for (int i = 0; i < list.size(); i++) {
                String sArticle = list.get(i);
                Article aArticle = JSON.parseObject(sArticle, Article.class);
                if (aArticle.getId() == article.getId()) {
                    String s = JSON.toJSONString(article);
                    stringRedisTemplate.opsForList().set(REDIS_ARTICLES_KEY, i, s);
                }
            }
        }
        return affectRow > 0;
    }

    public boolean deleteArticle(Long id) {
        int affectRow = mapper.deleteByid(id);
        //移除缓存
        String stringArticle = returnStringArticle(id);
        if (!StringUtils.isEmpty(stringArticle)) {
            stringRedisTemplate.opsForList().remove(REDIS_ARTICLES_KEY, 0, stringArticle);
        }
        return affectRow > 0;
    }

    public CommonPageInfo<Article> getPageList(Integer pageNum, Integer pageSize) {
        List<Article> articles = null;
        //redis返回 包前包后 所以结束位置需要减一
        //缓存读取起始位置start = pageSize * (pageNum - 1)；
        //缓存读取结束位置end = pageSize * (pageNum - 1) + (pageSize-1)
        int start = pageSize * (pageNum - 1);
        int end = start + pageSize - 1;
        //如果缓存中存在articles 并且列表长度大于 查询条数 注意分页
        List<String> articlesString = stringRedisTemplate.opsForList().range(REDIS_ARTICLES_KEY, start, end);
        if (articlesString.size() > 0) {
            //将json字符串集合转成article集合
            articles = changeStringToArticle(articlesString);
        } else {
            PageHelper.startPage(pageNum, pageSize);
            articles = mapper.findAll();
            //将文章列表放在redis缓存中
            List<String> stringarticles = changeArticleToString(articles);
            stringRedisTemplate.opsForList().rightPushAll(REDIS_ARTICLES_KEY, stringarticles);
        }
        //返回分页后对象
        PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
        return CommonPageInfo.getCommonPage(pageInfo);
    }

    //如果传入集合是String类型转成Article
    private List<Article> changeStringToArticle(List<String> list) {
        List<Article> dest = new ArrayList();
        for (String s : list) {
            Article article = JSON.parseObject(s, Article.class);
            dest.add(article);
        }
        return dest;
    }

    //如果传入集合是Article类型转成String
    private List<String> changeArticleToString(List<Article> list) {
        List<String> dest = new ArrayList();
        for (Article a : list) {
            String s = JSON.toJSONString(a);
            dest.add(s);
        }
        return dest;
    }

    //根据文章的id返回redis的字符串文章
    private String returnStringArticle(Long id) {
        if (stringRedisTemplate.hasKey(REDIS_ARTICLES_KEY)) {
            List<String> sArticles = stringRedisTemplate.opsForList().range(REDIS_ARTICLES_KEY, 0, -1);
            List<Article> articles = changeStringToArticle(sArticles);
            for (Article a : articles) {
                if (a.getId() == id) {
                    String s = JSON.toJSONString(a);
                    return s;
                }
            }
        }
        return null;
    }

    //写入文章发表日志记录
    @Transactional(propagation = Propagation.REQUIRED)
    public void writeArticleLog(String handleType, Article article) {
        ArticleLog articleLog = new ArticleLog();
        articleLog.setCreateTime(new Date());
        articleLog.setOperation(handleType);
        articleLog.setParams(JSON.toJSONString(article));
        logMapper.insertLog(articleLog);
        //int i = 10 / 0;
    }
}
