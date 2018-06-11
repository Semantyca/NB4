package com.semantyca.nb.ui.outline;

import com.semantyca.nb.util.StringUtil;

import java.util.ArrayList;

public class OutlineEntry {

    public enum ACTIVE_MODE {START}

    public String caption;
    public String hint;
    public String icon;
    public String url;
    public String customID;
    public ACTIVE_MODE activeMode;
    public boolean disabled;

    private ArrayList<OutlineEntry> entries = new ArrayList<>();

    public OutlineEntry(String caption, String hint, String icon, String customID, String url, ACTIVE_MODE activeMode) {
        this.caption = caption;
        this.hint = hint;
        this.icon = icon;
        this.url = url;
        this.customID = customID;
        this.activeMode = activeMode;
    }

    public OutlineEntry(String caption, String hint, String icon, String customID, String url) {
        this.caption = caption;
        this.hint = hint;
        this.icon = icon;
        this.url = url;
        this.customID = customID;
    }

    public OutlineEntry(String caption, String hint, String customID, String url) {
        this.caption = caption;
        this.hint = hint;
        this.url = url;
        this.customID = customID;
    }

    public OutlineEntry(String caption, String customID, String url) {
        this.caption = caption;
        this.hint = caption;
        this.customID = customID;
        this.url = url;
    }

    public OutlineEntry(String caption) {
        this.caption = caption;
        customID = StringUtil.getRndText();
    }

    public OutlineEntry disabled() {
        this.disabled = true;
        return this;
    }

    public void addEntry(OutlineEntry entry) {
        entries.add(entry);
    }

    public ArrayList<OutlineEntry> getEntries() {
        return entries;
    }
}
