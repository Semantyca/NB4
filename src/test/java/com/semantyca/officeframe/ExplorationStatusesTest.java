package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExplorationStatusesTest extends AbstractTest<ExplorationStatus> {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/explorationstatuses";

    @Test
    public void addExplorationStatuses() throws Exception {
        String[] statuses = {"planned", "complete", "failed"};

        for (String identifier : statuses) {

            ExplorationStatus entity = getEntity(BASE_SERVICE_URL + "/" + identifier);
            ExplorationStatus newEntity = new ExplorationStatus();
            if (entity == null) {
                newEntity.setTitle(identifier);
                newEntity.setIdentifier(identifier);
            } else {
                newEntity = entity;
            }

            Response generalResp = save(BASE_SERVICE_URL, newEntity);
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

    public List<ExplorationStatus> getExplorationStatuses(List<Object> providers) {
        return getEntities(BASE_SERVICE_URL);
    }
}
