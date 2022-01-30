package com.yushun.figure.vo.cmn;

import com.alibaba.excel.annotation.ExcelProperty;

public class DictExcelVo {

    @ExcelProperty(value = "ID", index = 0)
    private Long id;

    @ExcelProperty(value = "Name", index = 1)
    private String name;

    @ExcelProperty(value = "Parent ID", index = 2)
    private Long parentId;

    @ExcelProperty(value = "Value", index = 3)
    private String value;

    @ExcelProperty(value = "Dict Code", index = 4)
    private String dictCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}
