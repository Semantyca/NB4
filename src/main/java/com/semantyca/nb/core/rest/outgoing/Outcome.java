package com.semantyca.nb.core.rest.outgoing;

import com.semantyca.nb.core.dataengine.jpa.ISimpleAppEntity;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.ui.action.ActionBar;
import com.semantyca.nb.ui.view.ViewPage;
import com.semantyca.nb.ui.workspace.IconSet;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.List;

@XmlRootElement
public class Outcome {
    public static final String STRING_PAYLOAD = "text";
    public static final String ENTITY_PAYLOAD = "entity";
    public static final String VIEW_PAGE_PAYLOAD = "view";
    public static final String ENTITY_LIST_PAYLOAD = "list";
    public static final String ACTION_BAR_PAYLOAD = "actions";
    public static final String EXCEPTION_PAYLOAD = "error";
    public static final String ICONS_SET_PAYLOAD = "icons";

    private final static String UNDEFINED_ID = "undefined";
    private String id = UNDEFINED_ID;
    private String title = EnvConst.APP_ID;
    private String payloadTitle = EnvConst.APP_ID;
    private LinkedHashMap<String, Object> payload = new LinkedHashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Outcome setTitle(String title) {
        this.title = title;
        if (payloadTitle == null){
            payloadTitle = title;
        }
        return this;
    }

    public Outcome addPayload(String key, Object val) {
        payload.put(key, val);
        return this;
    }

    public Outcome addPayload(ISimpleAppEntity val) {
        payload.put(ENTITY_PAYLOAD, val);
        return this;
    }

    public LinkedHashMap<String, Object> getPayload() {
        return payload;
    }

    public Outcome setPayloadTitle(String title) {
        payloadTitle = title;
        if (this.title == null){
            this.title = title;
        }
        return this;
    }

    public Outcome addPayload(ViewPage vp) {
        payload.put(VIEW_PAGE_PAYLOAD, vp);
        return this;
    }

    public <T> Outcome addPayload(List<T> list) {
        payload.put(ENTITY_LIST_PAYLOAD, list);
        return this;
    }

    public Outcome addPayload(ActionBar actionBar) {
        payload.put(ACTION_BAR_PAYLOAD, actionBar);
        return this;
    }

    public Outcome addPayload(Exception e) {
        payload.put(EXCEPTION_PAYLOAD, e);
        return this;
    }

    public Outcome addPayload(IconSet iconSet) {
        payload.put(ICONS_SET_PAYLOAD, iconSet);
        return this;
    }

    public Outcome addPayload(String s) {
        payload.put(STRING_PAYLOAD, s);
        return this;
    }


}
