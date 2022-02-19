package com.yushun.figure.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yushun.figure.model.base.BaseEntity;

import java.util.Date;

@TableName("people")
public class People extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Long userId;

    @TableField("name")
    private String name;

    @TableField("sex")
    private Integer sex;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField("certificates_type")
    private String certificatesType;

    @TableField("certificates_no")
    private String certificatesNo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("birthdate")
    private Date birthdate;

    @TableField("status")
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
