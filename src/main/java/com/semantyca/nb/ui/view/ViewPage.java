package com.semantyca.nb.ui.view;

import com.semantyca.nb.ui.filter.FilterForm;

import java.util.List;

public class ViewPage {
    private List result;
    private long count;
    private int pageNum;
    private String keyword;
    private ViewOption option;
    private FilterForm filter;
    private int pageSize;
    private String meta;

    public ViewPage(String meta, List result, long count, int pageSize, int pageNum) {
        this.meta = meta;
        this.result = result;
        this.count = count;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public ViewPage(String meta, List result, long count, int pageSize, int pageNum, String k) {
        this(meta, result, count, pageSize, pageNum);
        keyword = k;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyWord) {
        this.keyword = keyWord;
    }

    public void setViewPageOptions(ViewOption viewOption) {
        this.option = viewOption;
    }

    public ViewOption getOption() {
        return option;
    }

    public FilterForm getFilter() {
        return filter;
    }

    public void setFilter(FilterForm filter) {
        this.filter = filter;
    }

    public String getMeta() {
        return meta;
    }

}
