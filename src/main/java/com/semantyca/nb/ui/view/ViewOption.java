package com.semantyca.nb.ui.view;


import com.semantyca.nb.ui.constant.SelectionMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewOption {

    /**
     * "model kind": [view column group]
     * строка документа состоит из групп колонок
     * <p>
     * root опции ("root": [view column group]) применяются для заголовков
     */
    private Map<String, List<ViewColumnGroup>> options = new HashMap<>();

    private SelectionMode selectionMode = SelectionMode.SINGLE;

    public void setRoot(List<ViewColumnGroup> vcg) {
        options.put("root", vcg);
    }

    public void add(String kind, List<ViewColumnGroup> vcg) {
        options.put(kind, vcg);
    }

    public Map<String, List<ViewColumnGroup>> getColumnOptions() {
        return options;
    }

    public SelectionMode getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
    }
}
