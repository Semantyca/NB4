package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExplorationStatusesTest extends AbstractTest<ExplorationStatus> {
    private static String BASE_SERVICE_URL = "http://" + APPLICATION_HOST + ":8080/nb4/OfficeFrame/explorationstatuses";

    @Test
    public void addExplorationStatuses() {
        String[] identifiers = {"planned", "complete", "failed"};
        String[] namesEng = {"Planned", "Complete", "Failed"};
        String[] namesRus = {"Запланированный", "Завершенный", "Неудавшийся"};
        String[] namesKaz = {"Жоспарланған", "Ааяқталды", "Сәтсіз аяқталды"};
        String[] colors = {"#e15258", "#51b46d", "#000000"};

        for (int i = 0; i < identifiers.length; i++) {

            ExplorationStatus entity = getEntity(BASE_SERVICE_URL + "/" + identifiers[i]);
            ExplorationStatus newEntity;
            if (entity == null) {
                newEntity = new ExplorationStatus();
                newEntity.setTitle(identifiers[i]);
                newEntity.setIdentifier(identifiers[i]);
            } else {
                newEntity = entity;
            }
            Map<LanguageCode, String> name = new HashMap<>();
            name.put(LanguageCode.RUS, namesRus[i]);
            name.put(LanguageCode.ENG, namesEng[i]);
            name.put(LanguageCode.KAZ, namesKaz[i]);
            newEntity.setLocName(name);
            newEntity.setColor(colors[i]);

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
        List<ExplorationStatus> entities = getExplorationStatuses(providers);
        for (ExplorationStatus status : entities) {
            System.out.println(status);
        }

    }

    public List<ExplorationStatus> getExplorationStatuses(List<Object> providers) {
        return getEntities(BASE_SERVICE_URL);
    }
}
