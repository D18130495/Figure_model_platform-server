package com.yushun.figure.common.result;

public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    FAIL(201, "fail"),

    LOGIN_AUTH(208, "Not login"),
    PERMISSION(209, "Not Auth");

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
