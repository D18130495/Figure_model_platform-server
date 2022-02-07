package com.yushun.figure.vo.company;

import java.util.List;

public class SeriesTreeVo {

    private String seriesCode;

    private String seriesName;

    private List<SeriesTreeVo> children;

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public List<SeriesTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<SeriesTreeVo> children) {
        this.children = children;
    }
}
