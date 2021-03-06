package com.yushun.figure.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;

public class UserAuthVo {
    @TableField("name")
    private String name;

    @TableField("certificates_type")
    private String certificatesType;

    @TableField("certificates_no")
    private String certificatesNo;

    @TableField("certificates_url")
    private String certificatesUrl;

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
}
