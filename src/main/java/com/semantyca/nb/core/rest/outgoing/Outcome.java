package com.semantyca.nb.core.rest.outgoing;

import com.semantyca.nb.core.env.EnvConst;

import java.util.LinkedHashMap;

public class Outcome {
    public final static String UNDEFINED_ID = "undefined";
    protected String id = UNDEFINED_ID;
    protected String title = EnvConst.APP_ID;
    protected LinkedHashMap<String, Object> payload = new LinkedHashMap<>();

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
        return this;
    }

    public Outcome setPayload(LinkedHashMap<String, Object> payload) {
        this.payload = payload;
        return this;
    }

    public Outcome addPayload(String key, Object payload) {
        this.payload.put(key, payload);
        return this;
    }

    public LinkedHashMap<String, Object> getPayload() {
        return payload;
    }
}
