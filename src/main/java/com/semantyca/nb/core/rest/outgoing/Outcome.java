package com.semantyca.nb.core.rest.outgoing;

import com.semantyca.nb.core.env.EnvConst;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;

@XmlRootElement
public class Outcome {
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
        return this;
    }

    public Outcome addPayload(String key, Object val) {
        payload.put(key, val);
        return this;
    }

    public Outcome addPayload(Object val) {
        payload.put(val.getClass().getSimpleName().toLowerCase(), val);
        return this;
    }

    public LinkedHashMap<String, Object> getPayload() {
        return payload;
    }

    public Outcome setPayloadTitle(String title) {
        payloadTitle = title;
        return this;
    }
}
