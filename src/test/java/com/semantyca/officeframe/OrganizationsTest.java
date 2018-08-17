package com.semantyca.officeframe;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.organizations.model.Organization;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.ConfigurableJohnzonProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class OrganizationsTest {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/organizations";
    private static List<Object> providers = new ArrayList<Object>();
    List<Organization> entities = new ArrayList<Organization>();
    private String[] data = {"kazuav"};
    private String[] dataEng = {"KazUAV"};
    private String[] dataRus = {"КазУАВ"};
    private String[] dataKaz = {"КазУАВ"};

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        providers.add(new ConfigurableJohnzonProvider());
    }


    @Test
    public void addExplorationStatuses() throws Exception {

        for (int i = 0; i < data.length; i++) {
            boolean isNew = false;
            Response resp = WebClient.create(BASE_SERVICE_URL + "/" + data[i], providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(Response.class);

            int status = resp.getStatus();
            Organization existEntity = null;
            if (status == 200) {
                Outcome outcome = resp.readEntity(Outcome.class);
                try {
                    Map entity = (Map) outcome.getPayload().get(Outcome.ENTITY_PAYLOAD);
                    Jsonb jsonb = JsonbBuilder.create();
                    existEntity = jsonb.fromJson(jsonb.toJson(entity), Organization.class);
                    entities.add(existEntity);
                } catch (Exception e) {

                }
            }

            Organization newEntity = new Organization();
            if (existEntity == null) {
                isNew = true;
                newEntity.setTitle(data[i]);
                newEntity.setIdentifier(data[i]);
                Map<LanguageCode, String> name = new HashMap<LanguageCode, String>();
                name.put(LanguageCode.ENG, dataEng[i]);
                name.put(LanguageCode.RUS, dataRus[i]);
                name.put(LanguageCode.KAZ, dataKaz[i]);
                newEntity.setLocName(name);
            } else {
                newEntity = existEntity;
            }

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


}
