package com.semantyca.extconnect;


import com.semantyca.administrator.com.semantyca.extconnect.httpclient.ClientProvider;
import com.semantyca.nb.logger.Lg;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class Requester<T> {
    protected String defaultServiceURL;

    private String method = HttpMethod.GET;
    private Form formData = new Form();

    public Requester(String url) {
        defaultServiceURL = url;
    }


    public Map<String, ?> getData() {
        Client client = new ClientProvider().get();
        WebTarget target = client.target(defaultServiceURL);
        Response response = null;
        try {
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            response = invocationBuilder.get();
            Map res = response.readEntity(Map.class);
            int status = response.getStatus();
            System.out.println(status + " " + res);
            return res;

        } finally {
            if (response != null) {
                response.close();
            }
        }

    }

    public T postObject(Class<T> type, Object entity) {
        Client client = new ClientProvider().get();
        WebTarget target = client.target(defaultServiceURL);
        Response response = null;
        try {
            Invocation.Builder invocationBuilder = target.request();
            response = invocationBuilder.post(Entity.entity(entity, MediaType.APPLICATION_JSON), Response.class);
            int status = response.getStatus();
            T res = response.readEntity(type);

            System.out.println(status + " " + res);
            return res;
        } catch (Exception e) {
            Lg.error(e);
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


}
