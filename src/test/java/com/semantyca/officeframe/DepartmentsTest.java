package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.organizations.model.Department;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.ConfigurableJohnzonProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class DepartmentsTest extends AbstractTest<Department> {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/departments";
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
            Department existEntity = getEntity(BASE_SERVICE_URL + "/" + data[i]);

            Department newEntity = new Department();
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
