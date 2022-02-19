package com.yushun.figure.common.result;

public enum AuthStatusEnum {

    NO_AUTH("No authentication", 0,"No Auth"),
    AUTH_RUN("Authentication in process", 1, "Auth in process"),

    AUTH_SUCCESS("Authenticated", 2, "Auth success"),
    PERMISSION("Authentication failed", -1, "Auth failed");

    private String name;
    private Integer status;
    private String message;

    private AuthStatusEnum(String name, Integer status, String message) {
        this.name = name;
        this.status = status;
        this.message = message;
    }

    public static String getStatusNameByStatus(Integer status) {
        AuthStatusEnum arrObj[] = AuthStatusEnum.values();
        for(AuthStatusEnum obj : arrObj) {
            if(status.intValue() == obj.status.intValue()) {
                return obj.getName();
            }
        }
        return "";
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
