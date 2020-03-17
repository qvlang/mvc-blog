package com.lang.blog.model.result;


public enum ResultCode {
    SUCCESS(200L,"请求成功"),
    FAIL(401L,"请求失败");
    private Long code;
    private String message;
    private ResultCode(Long code,String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
