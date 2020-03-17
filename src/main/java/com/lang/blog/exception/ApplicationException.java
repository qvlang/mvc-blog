package com.lang.blog.exception;

import com.lang.blog.model.result.CommonResult;
import org.csource.common.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
/*
*  全局异常处理
* */

@RestControllerAdvice
public class ApplicationException {
    /**
     * @Description: 文件异常
     * @Param:
     * @return:
     */
    @ExceptionHandler({IOException.class})
    public CommonResult<String> fileExceptionHandle(Exception io) {
        return CommonResult.FAILED(io.getMessage());
    }
}
