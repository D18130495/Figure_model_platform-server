package com.yushun.figure.model.cmn;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yushun.figure.model.base.BaseEntity;

@TableName("dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    @TableField("value")
    private String value;

    @TableField("dict_code")
    private String dictCode;

    @TableField(exist = false)
    private boolean hasChildren;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
