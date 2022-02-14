package com.yushun.figure.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yushun.figure.model.base.BaseEntity;

public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("openid")
    private String openid;

    @TableField("nick_name")
    private String nickName;

    @TableField("phone")
    private String phone;

    @TableField("name")
    private String name;

    @TableField("certificates_type")
    private String certificatesType;

    @TableField("certificates_no")
    private String certificatesNo;

    @TableField("certificates_url")
    private String certificatesUrl;

    @TableField("auth_status")
    private Integer authStatus;

    @TableField("status")
    private Integer status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesNo() {
        return certificatesNo;
    }

    public void setCertificatesNo(String certificatesNo) {
        this.certificatesNo = certificatesNo;
    }

    public String getCertificatesUrl() {
        return certificatesUrl;
    }

    public void setCertificatesUrl(String certificatesUrl) {
        this.certificatesUrl = certificatesUrl;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}