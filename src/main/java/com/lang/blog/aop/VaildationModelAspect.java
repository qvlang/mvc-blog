package com.lang.blog.aop;

import com.lang.blog.model.result.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;

/**
 * 使用aop的步骤
 * 1引入对应坐标
 * 2在配置文件中启动<aop:aspectj-autoproxy proxy-target-class="true" /> 或者注解enable
 * 3配置切面类
 * 切面通知类型
 * 1 前置通知
 * 2 返回通知
 * 3 错误通知
 * 4 后置通知
 * 5 环绕通知 在使用注解时 需要使用环绕通知 其他几种不能按要求执行
 */
@Aspect
@Component
public class VaildationModelAspect {
    //环绕通知 1包4 它有特别的ProceedingJoinPoint 其他类型的通知是JoinPoint
    //定义切入点表达式
    @Pointcut("execution(public * com.lang.blog.controller.*Controller.*(..))")
    public void declearJoinPointExpression() {
    }

    @Around("declearJoinPointExpression()")
    public Object around(ProceedingJoinPoint point) {
        Object returnValue = null;
        try {
            //前置通知 在方法调用前验证是否数据校验出错 出错则直接返回错误信息
            Object[] args = point.getArgs(); //拿到参数bindresult
            for (Object obj : args) {
                if (obj instanceof BindingResult) {
                    BindingResult result = (BindingResult) obj;
                    int errorCount = result.getErrorCount();
                    //if errorCount > 0 则需要处理错误
                    if (errorCount > 0) {
                        String failInfo = "";
                        List<FieldError> fieldErrors = result.getFieldErrors();
                        for (FieldError error : fieldErrors) {
                            failInfo += error.getDefaultMessage();
                        }
                        return CommonResult.FAILED(failInfo);
                    }
                }
            }
            returnValue = point.proceed(point.getArgs());//相当于代理的 method.invoke()
        } catch (Throwable e) {
            throw new RuntimeException(e); //异常通知
        }
        return returnValue;
    }
}
