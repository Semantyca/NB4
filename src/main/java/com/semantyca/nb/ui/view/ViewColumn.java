package com.semantyca.nb.ui.view;

public class ViewColumn {

    public String name;
    public String value; // string | string.string.string; // model field identifier or normalized data value path
    /*
     * valueFn
     * function body. example: return it.field
     * model argument identifier: it
     */
    public String valueFn;
    public ViewColumnType type = ViewColumnType.text;
    public String hint;
    public String icon;
    public String monogram;
    public Sort sort;
    public String className; // css class identifier
    public String valueAsClass; // add value as class {valueAsClass + model[value]}
    /*
     * cellStyle
     * function body. example: return { color:it.color }
     * model argument identifier: it
     */
    public String cellStyle;
    /*
     * style for value text
     * function body. example: return { color:'red' }
     * model argument identifier: it
     */
    public String style;
    public String format; // date format

    public ViewColumn() {
    }

    public ViewColumn(String value) {
        this.value = value;
        this.name = value;
    }

    public ViewColumn name(String name) {
        this.name = name;
        return this;
    }

    public ViewColumn value(String value) {
        this.value = value;
        return this;
    }

    public ViewColumn valueFn(String valueFn) {
        this.valueFn = valueFn;
        return this;
    }

    public ViewColumn type(ViewColumnType type) {
        this.type = type;
        return this;
    }

    public ViewColumn hint(String hint) {
        this.hint = hint;
        return this;
    }

    public ViewColumn icon(String icon) {
        this.icon = icon;
        return this;
    }

    public ViewColumn monogram(String monogram) {
        this.monogram = monogram;
        return this;
    }

    public ViewColumn sortAsc() {
        this.sort = Sort.asc;
        return this;
    }

    public ViewColumn sortDesc() {
        this.sort = Sort.desc;
        return this;
    }

    public ViewColumn sortBoth() {
        this.sort = Sort.both;
        return this;
    }

    public ViewColumn className(String className) {
        this.className = className;
        return this;
    }

    public ViewColumn valueAsClass(String valueAsClass) {
        this.valueAsClass = valueAsClass;
        return this;
    }

    public ViewColumn cellStyle(String cellStyle) {
        this.cellStyle = cellStyle;
        return this;
    }

    public ViewColumn style(String style) {
        this.style = style;
        return this;
    }

    public ViewColumn format(String format) {
        this.format = format;
        return this;
    }

    private enum Sort {asc, desc, both}
}
