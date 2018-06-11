package com.semantyca.nb.ui.view;

import java.util.ArrayList;
import java.util.List;


public class ViewColumnGroup {

    private String title; // group title
    private String className; // css class for column group
    private List<ViewColumn> columns = new ArrayList<>();

    public ViewColumnGroup() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ViewColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ViewColumn> columns) {
        this.columns = columns;
    }

    public void add(ViewColumn column) {
        this.columns.add(column);
    }
}
