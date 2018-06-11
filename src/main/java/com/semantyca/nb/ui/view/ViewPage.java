package com.semantyca.nb.ui.view;


import com.semantyca.nb.ui.filter.FilterForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ViewPage<T> {

    private List<T> result;
    private long count;
    private int maxPage;
    private int pageNum;
    private String keyword;
    private ViewOption option;
    private FilterForm filter;

    public ViewPage(List<T> result, long count, int maxPage, int pageNum) {
        this.result = result;
        this.count = count;
        this.maxPage = maxPage;
        this.pageNum = pageNum;
    }

    public ViewPage(Object result, long count, int maxPage, int pageNum, boolean showWasRead) {
        this.result = (List<T>) Arrays.asList((T) result).get(0);
        this.count = count;
        this.maxPage = maxPage;
        this.pageNum = pageNum;
    }

    public ViewPage(List<T> result, long count, int maxPage, int pageNum, String k) {
        this(result, count, maxPage, pageNum);
        keyword = k;
    }

    public ViewPage() {
        this(new ArrayList<T>(), 0, 1, 1);
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
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

    public void merge(ViewPage<T> vp) {
        this.result.addAll(vp.result);
        this.count = count + vp.count;
        this.maxPage = maxPage + vp.maxPage;
    }
}
