package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.reference.model.Position;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class PositionsTest extends AbstractTest<Position> {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/positions";
    List<Position> entities = new ArrayList<Position>();
    private String[] data = {"ceo", "manager", "accounter", "engineer", "specialist", "secretary", "administrator",
            "department_manager", "forwarder"};
    private String[] dataEng = {"CEO", "Manager", "Accounter", "Engineer", "Specialist", "Secretary", "Administrator",
            "Department manager", "Forwarder"};
    private String[] dataRus = {"Директор", "Менеджер", "Бухгалтер", "Инженер", "Специалист", "Секретарь-референт",
            "Администратор", "Руководитель подразделения", "Экспедитор"};
    private String[] dataKaz = {"Директор", "Менеджер", "Бухгалтер", "Инженер", "Маман", "Хатшы-референт",
            "Әкімші", "Бөлім бастығы", "Экспедитор"};
    private int[] rankData = {5, 7, 6, 6, 8, 10, 6, 8, 13};


    @Test
    public void addPositions() {

        for (int i = 0; i < data.length; i++) {
            Position existEntity = getEntity(BASE_SERVICE_URL + "/" + data[i]);

            Position newEntity = new Position();
            if (existEntity == null) {
                newEntity.setTitle(data[i]);
                newEntity.setIdentifier(data[i]);
                Map<LanguageCode, String> name = new HashMap<LanguageCode, String>();
                name.put(LanguageCode.ENG, dataEng[i]);
                name.put(LanguageCode.RUS, dataRus[i]);
                name.put(LanguageCode.KAZ, dataKaz[i]);
                newEntity.setLocName(name);
                newEntity.setRank(rankData[i]);
            } else {
                newEntity = existEntity;
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


}
