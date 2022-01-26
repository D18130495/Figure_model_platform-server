package com.yushun.figure.model.company;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yushun.figure.model.base.BaseEntity;

@TableName("company_set")
public class CompanySet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField("comp_name")
    private String compName;

    @TableField("comp_code")
    private String compCode;

    @TableField("api_url")
    private String apiUrl;

    @TableField("sign_key")
    private String signKey;

    @TableField("contacts_name")
    private String contactsName;

    @TableField("contacts_phone")
    private String contactsPhone;

    @TableField("status")
    private Integer status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
