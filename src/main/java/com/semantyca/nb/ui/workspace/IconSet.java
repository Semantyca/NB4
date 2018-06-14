package com.semantyca.nb.ui.workspace;

import java.util.ArrayList;
import java.util.List;

public class IconSet {
    private List<Icon> icons = new ArrayList<>();

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }

    public IconSet add(Icon val) {
        icons.add(val);
        return this;
    }
}
