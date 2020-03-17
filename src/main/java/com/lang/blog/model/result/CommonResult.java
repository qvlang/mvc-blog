package com.lang.blog.model.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"javassistProxyFactory", "handler"})
public class CommonResult<T> implements Serializable {
    private static final Long serialVersionUID = 6564121545l;
    private T data;
    private Long code;
    private String message;

    private CommonResult(Long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult SUCCESS(T data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> FAILED(T data) {
        return new CommonResult(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
