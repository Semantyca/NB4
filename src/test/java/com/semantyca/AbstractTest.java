package com.semantyca;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.officeframe.modules.organizations.model.Organization;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.ConfigurableJohnzonProvider;
import org.junit.BeforeClass;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractTest<T> {
    protected static List<Object> providers = new ArrayList<Object>();
    private Class<T> entityClass;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        providers.add(new ConfigurableJohnzonProvider());
    }

    protected T getEntity(String endPoint) {
        Response resp = WebClient.create(endPoint, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        int status = resp.getStatus();
        T existEntity = null;
        if (status == 200) {
            Outcome outcome = resp.readEntity(Outcome.class);
            try {
                Map entity = (Map) outcome.getPayload().get(Outcome.ENTITY_PAYLOAD);
                Jsonb jsonb = JsonbBuilder.create();
                existEntity = (T) jsonb.fromJson(jsonb.toJson(entity), Organization.class);
            } catch (Exception e) {

            }
        }
        return existEntity;

    }

}
