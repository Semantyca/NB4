package com.semantyca.skyra;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.util.ListUtil;
import com.semantyca.nb.util.StringUtil;
import com.semantyca.nb.util.TimeUtil;
import com.semantyca.officeframe.ExplorationStatusesTest;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import com.semantyca.skyra.modules.operator.model.Exploration;
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

public class ExplorationsTest {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/Skyra/explorations";
    private static List<Object> providers = new ArrayList<Object>();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        providers.add(new ConfigurableJohnzonProvider());
    }

    @Test
    public void deleteExplorations() throws Exception {
        List<String> entities = new ArrayList();

        Response resp = WebClient.create(BASE_SERVICE_URL, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
        int status = resp.getStatus();
        if (status == 200) {
            Outcome outcome = resp.readEntity(Outcome.class);
            Map vp = (Map) outcome.getPayload().get(Outcome.VIEW_PAGE_PAYLOAD);
            System.out.println("found " + vp.get("count"));
            List<Map> list = (List<Map>) vp.get("result");
            Mapper builder = new MapperBuilder().build();
            for (Map element : list) {
                Jsonb jsonb = JsonbBuilder.create();
                //redundant
                Exploration exploration = jsonb.fromJson(jsonb.toJson(element), Exploration.class);
                entities.add(exploration.getId().toString());
            }
        }

        assertEquals(200, status);

        for (String u : entities) {
            Response generalResp = null;
            generalResp = WebClient.create(BASE_SERVICE_URL + "/" + u, providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.APPLICATION_JSON)
                    .delete();


            int generalRespStatus = generalResp.getStatus();
            assertEquals(204, generalRespStatus);
            if (generalRespStatus == 204) {
                System.out.println(u + " deleted");
            }
        }
    }

    @Test
    public void addTestExplorations() throws Exception {
        List<String> data = new ArrayList();

        for (int i = 0; i < 100; i++) {
            data.add(StringUtil.getRndText());
        }

        for (String u: data) {
            boolean isNew = false;
            Response resp = WebClient.create(BASE_SERVICE_URL + "/" + u, providers)
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

            Exploration newEntity = null;
            if (entity == null) {
                isNew = true;
                newEntity = new Exploration();
                List<ExplorationStatus> statuses = ExplorationStatusesTest.getExplorationStatuses(providers);
                ExplorationStatus explorationStatus = (ExplorationStatus) ListUtil.getRndElement(statuses);
                newEntity.setStatus(explorationStatus);
                newEntity.setStartTime(TimeUtil.getRndDateTime());
            } else {

            }

            newEntity.setTitle(u);

            Response generalResp = null;
            if (isNew) {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .type(MediaType.APPLICATION_JSON)
                        .post(newEntity, Response.class);
            } else {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
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
}
