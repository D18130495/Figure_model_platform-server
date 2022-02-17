package com.yushun.figure.common.result;

public enum AuthStatusEnum {

    NO_AUTH(0,"No Auth"),
    AUTH_RUN(1, "Auth in process"),

    AUTH_SUCCESS(2, "Auth success"),
    PERMISSION(-1, "Auth failed");

    private Integer status;
    private String message;

    private AuthStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
