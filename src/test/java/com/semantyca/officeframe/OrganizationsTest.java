package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.util.ListUtil;
import com.semantyca.officeframe.modules.organizations.model.Organization;
import com.semantyca.officeframe.modules.reference.model.OrgCategory;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class OrganizationsTest extends AbstractTest<Organization> {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/organizations";
    private String[] data = {"kazuav"};
    private String[] dataEng = {"KazUAV"};
    private String[] dataRus = {"КазУАВ"};
    private String[] dataKaz = {"КазУАВ"};

    @Test
    public void addOrganizations() throws Exception {


        for (int i = 0; i < data.length; i++) {
            Organization existEntity = getEntity(BASE_SERVICE_URL + "/" + data[i]);
            Organization newEntity = new Organization();
            if (existEntity == null) {
                newEntity.setTitle(data[i]);
                newEntity.setIdentifier(data[i]);
                Map<LanguageCode, String> name = new HashMap<LanguageCode, String>();
                name.put(LanguageCode.ENG, dataEng[i]);
                name.put(LanguageCode.RUS, dataRus[i]);
                name.put(LanguageCode.KAZ, dataKaz[i]);
                newEntity.setLocName(name);
                List<OrgCategory> categories = new OrgCategoriesTest().getOrgCategories();
                assertNotNull(categories);
                newEntity.setOrgCategory((OrgCategory) ListUtil.getRndElement(categories));

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

    @Test
    public void getOrganizationTest() {
        List<Organization> entities = getEntities(BASE_SERVICE_URL);
        for (Organization organization : entities) {
            System.out.println(organization);
        }
    }

    public List<Organization> getOrganizations() {
        return getEntities(BASE_SERVICE_URL);

    }
}
