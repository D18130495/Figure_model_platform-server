package com.yushun.figure.common.result;

/**
 * 统一返回结果状态信息类
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    FAIL(201, "fail");

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
