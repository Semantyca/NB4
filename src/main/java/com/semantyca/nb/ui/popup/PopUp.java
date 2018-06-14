package com.semantyca.nb.ui.popup;

public class PopUp {
    private PopUpType type;
    private String title;
    private String message;
    private boolean progress;

    public PopUp(PopUpType type, String title) {
        this.type = type;
        this.title = title;
    }

    public PopUp message(String message) {
        this.message = message;
        return this;
    }

    public PopUp progress() {
        progress = true;
        return this;
    }

    public PopUpType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }
}
