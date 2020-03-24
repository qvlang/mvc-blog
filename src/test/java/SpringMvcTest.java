import com.alibaba.fastjson.JSON;
import com.lang.blog.model.Article;
import com.lang.blog.model.ArticleCategory;
import com.lang.blog.service.IUserService;
import com.lang.blog.utils.DeepCopyObjectOrList;
import com.lang.blog.utils.FastDfsUtil;
import org.csource.common.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpringMvcTest {


    @Test
    public void testFastFile() throws IOException, MyException {
        //将文件转成字节数组
        FileInputStream fis = new FileInputStream("file.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = fis.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        String filename = FastDfsUtil.uploadFile(bytes, "txt");
        System.out.println(filename);
        fis.close();
        baos.close();
    }

    @Test
    public void deepCopy() {
        Article article = new Article();
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setName("2020世界");
        article.setAuthorId(3l);
        article.setCategory("66");
        article.setKind(articleCategory);
        Article article1 = DeepCopyObjectOrList.DeepCopyObject(article);
        Article article2 = DeepCopyObjectOrList.DeepCopyObject(article);
        article.setAuthorId(2l);
        article1.getKind().setName("2001");
        System.out.println(article);
        System.out.println(article1);
        System.out.println(article2);
        List<Article> list = new ArrayList<>();
        list.add(article);
        list.add(article1);
        list.add(article2);
        List<Article> articles = DeepCopyObjectOrList.DeepCopyObject(list);
        list.stream().forEach((article6) -> {
            JSON.toJSONString(article6);
        });
        for (Article a : list) {
            System.out.println(a);
        }
        for (Article b : articles) {
            System.out.println(b);
        }
    }
}
