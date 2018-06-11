package com.semantyca.nb.ui.outline;

import java.util.ArrayList;
import java.util.List;

public class Outline {

    public String caption;
    public String hint;
    public String customID;

    private List<OutlineEntry> entries = new ArrayList<>();

    public Outline(String caption, String hint, String customID) {
        this.caption = caption;
        this.hint = hint;
        this.customID = customID;
    }

    public Outline(String caption, String customID) {
        this.caption = caption;
        this.customID = customID;
    }

    public Outline(String customID) {
        this.caption = customID;
        this.customID = customID;
    }

    public void addEntry(OutlineEntry entry) {
        entries.add(entry);
    }

    public List<OutlineEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<OutlineEntry> entries) {
        this.entries = entries;
    }
}
