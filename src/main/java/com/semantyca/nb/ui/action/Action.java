package com.semantyca.nb.ui.action;


import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.localization.Vocabulary;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.ui.action.constants.ActionPayloadType;
import com.semantyca.nb.ui.action.constants.ActionType;
import com.semantyca.nb.ui.constant.SelectionMode;
import com.semantyca.nb.ui.popup.PopUp;
import com.semantyca.nb.ui.popup.PopUpType;

import javax.ws.rs.HttpMethod;


public class Action {

    private ActionType type;
    private ActionPayloadType payloadType;
    private SelectionMode target;
    private String method = HttpMethod.GET;
    private String url;

    private String customID;
    private String caption;
    private String hint;
    private String icon;
    private String className;
    private Boolean hidden;

    private PopUp confirm;
    private PopUp notify;

    private Action onSuccess;

    public Action() {
        this(ActionType.API_ACTION);
    }

    public Action(ActionType type) {
        this.type = type;
        switch (type) {
            case SAVE_AND_CLOSE:
                method = HttpMethod.POST;
                payloadType = ActionPayloadType.MODEL;
                notify(new PopUp(PopUpType.INFO, "wait_while_document_save").progress());
                onSuccess = new Action(ActionType.CLOSE);
                break;
            case CLOSE:
                caption = "close";
                hint = "just_close";
                break;
            case BACK:
                caption = "back";
                hint = "just_back";
                break;
            case DELETE_DOCUMENT:
                method = HttpMethod.DELETE;
                onSuccess = new Action(ActionType.CLOSE);
                break;
            case API_ACTION:
                method = HttpMethod.POST;
                payloadType = ActionPayloadType.ACTION_PAYLOAD;
                break;
            default:
                caption = "";
                hint = "";
        }
        customID = type.toString().toLowerCase();
    }

    public Action(String caption, String hint, ActionType type) {
        this.caption = caption;
        this.hint = hint;
        this.type = type;
        customID = type.toString().toLowerCase();
    }

    public Action(String caption, String hint, String customID) {
        this.caption = caption;
        this.hint = hint;
        this.type = ActionType.CUSTOM_ACTION;
        this.customID = customID;
    }

    void setupCaption(Session session, Vocabulary v) {
        LanguageCode lang = session.getLang();
        if (caption != null && !caption.isEmpty()) {
            caption = v.getWord(caption, lang);
            if (hint != null && !hint.isEmpty()) {
                hint = v.getWord(hint, lang);
            } else {
                if (!caption.isEmpty()) {
                    hint = caption;
                }
            }
        } else {
            caption = "";
            if (hint != null && !hint.isEmpty()) {
                hint = v.getWord(hint, lang);
            } else {
                hint = "";
            }
        }
    }

    public Action id(String id) {
        this.customID = id;
        return this;
    }

    public Action caption(String caption) {
        this.caption = caption;
        return this;
    }

    public Action hint(String hint) {
        this.hint = hint;
        return this;
    }

    public Action icon(String icon) {
        this.icon = icon;
        return this;
    }

    public Action cls(String cssClassName) {
        this.className = cssClassName;
        return this;
    }

    public Action url(String url) {
        this.url = url;
        return this;
    }

    public Action payloadType(ActionPayloadType payloadType) {
        this.payloadType = payloadType;
        return this;
    }

    public Action targetMultiple() {
        this.target = SelectionMode.MULTIPLE;
        return this;
    }

    public Action targetSingle() {
        this.target = SelectionMode.SINGLE;
        return this;
    }

    public Action hidden() {
        this.hidden = true;
        return this;
    }

    public Action method(String method) {
        this.method = method;
        return this;
    }

    public Action notify(PopUp notify) {
        this.notify = notify;
        return this;
    }

    public Action confirm(PopUp confirm) {
        this.confirm = confirm;
        return this;
    }

    public Action confirm() {
        return confirm(new PopUp(PopUpType.CONFIRM, "confirm_action"));
    }

    public Action onSuccess(Action onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }



    public ActionType getType() {
        return type;
    }

    public Action setType(ActionType type) {
        this.type = type;
        return this;
    }

    public ActionPayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(ActionPayloadType payloadType) {
        this.payloadType = payloadType;
    }

    public SelectionMode getTarget() {
        return target;
    }

    public void setTarget(SelectionMode target) {
        this.target = target;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCustomID() {
        return customID;
    }

    public void setCustomID(String customID) {
        this.customID = customID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public PopUp getNotify() {
        return notify;
    }

    public void setNotify(PopUp notify) {
        this.notify = notify;
    }

    public PopUp getConfirm() {
        return confirm;
    }

    public void setConfirm(PopUp confirm) {
        this.confirm = confirm;
    }

    public Action getOnSuccess() {
        return onSuccess;
    }

    public void setOnSuccess(Action onSuccess) {
        this.onSuccess = onSuccess;
    }
}
