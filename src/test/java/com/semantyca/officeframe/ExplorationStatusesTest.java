package com.semantyca.officeframe;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.ConfigurableJohnzonProvider;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExplorationStatusesTest {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/explorationstatuses";
    private static List<Object> providers = new ArrayList<Object>();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        providers.add(new ConfigurableJohnzonProvider());
    }


    @Test
    public void addExplorationStatuses() throws Exception {
        String[] statuses = {"planned", "complete", "failed"};

        for (String identifier : statuses) {
            boolean isNew = false;
            Response resp = WebClient.create(BASE_SERVICE_URL + "/" + identifier, providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(Response.class);

            Map entity = null;
            int status = resp.getStatus();
            if (status == 200) {
                Outcome outcome = resp.readEntity(Outcome.class);
                try {
                    entity = (Map) outcome.getPayload().get(Outcome.ENTITY_PAYLOAD);
                } catch (Exception e) {

                }
            }

            ExplorationStatus newEntity = new ExplorationStatus();
            if (entity == null) {
                isNew = true;
            } else {

            }

            newEntity.setTitle(identifier);
            newEntity.setIdentifier(identifier);
            Response generalResp = null;
            if (isNew) {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .type(MediaType.APPLICATION_JSON)
                        .post(newEntity, Response.class);
            } else {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .type(MediaType.APPLICATION_JSON)
                        .put(newEntity, Response.class);
            }

            int generalRespStatus = generalResp.getStatus();
            assertEquals(200, generalRespStatus);
            if (generalRespStatus == 200) {
                Outcome generalOutcome = generalResp.readEntity(Outcome.class);
                assertNotNull(generalOutcome);
            }
        }
    }

    @Test
    public void getExplorationStatusesTest() throws Exception {
        List<ExplorationStatus> statusList = getExplorationStatuses(providers);


    }

    public static List<ExplorationStatus> getExplorationStatuses(List<Object> providers) {
        List<ExplorationStatus> entities = new ArrayList<>();
        Response resp = WebClient.create(BASE_SERVICE_URL, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
        int status = resp.getStatus();
        if (status == 200) {
            Outcome outcome = resp.readEntity(Outcome.class);
            try {
                Map vp = (Map) outcome.getPayload().get(Outcome.VIEW_PAGE_PAYLOAD);
                List<Map> list = (List<Map>) vp.get("result");
                Mapper builder = new MapperBuilder().build();
                for (Map element : list) {
                    Jsonb jsonb = JsonbBuilder.create();
                    //redundant
                    ExplorationStatus sts = jsonb.fromJson(jsonb.toJson(element), ExplorationStatus.class);
                    entities.add(sts);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entities;
    }
}
