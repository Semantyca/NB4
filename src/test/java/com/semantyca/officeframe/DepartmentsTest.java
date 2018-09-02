package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.util.ListUtil;
import com.semantyca.nb.util.NumberUtil;
import com.semantyca.officeframe.modules.organizations.model.Department;
import com.semantyca.officeframe.modules.organizations.model.Organization;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class DepartmentsTest extends AbstractTest<Department> {
    private static String BASE_SERVICE_URL = "http://" + APPLICATION_HOST + ":8080/nb4/OfficeFrame/departments";
    private String[] data = {"production", "rd", "purchasing", "marketing", "hr", "af"};
    private String[] dataEng = {"Production", "Research and Development", "Purchasing", "Marketing", "Human Resource Management", "Accounting and Finance"};
    private String[] dataRus = {"Production", "Research and Development", "Purchasing", "Marketing", "Human Resource Management", "Accounting and Finance"};
    private String[] dataKaz = {"Production", "Research and Development", "Purchasing", "Marketing", "Human Resource Management", "Accounting and Finance"};


    @Test
    public void addDepartments() throws Exception {

        for (int i = 0; i < data.length; i++) {
            Department existEntity = getEntity(BASE_SERVICE_URL + "/" + data[i]);
            Department newEntity = new Department();
            if (existEntity == null) {
                newEntity.setTitle(data[i]);
                newEntity.setIdentifier(data[i]);
                Map<LanguageCode, String> name = new HashMap<LanguageCode, String>();
                name.put(LanguageCode.ENG, dataEng[i]);
                name.put(LanguageCode.RUS, dataRus[i]);
                name.put(LanguageCode.KAZ, dataKaz[i]);
                newEntity.setLocName(name);
                newEntity.setOrganization((Organization) ListUtil.getRndElement(new OrganizationsTest().getOrganizations()));
                newEntity.setRank(NumberUtil.getRandomNumber(0, 100));
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
