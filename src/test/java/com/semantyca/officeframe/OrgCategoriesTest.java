package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.reference.model.OrgCategory;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class OrgCategoriesTest extends AbstractTest<OrgCategory> {
    private static String BASE_SERVICE_URL = "http://" + APPLICATION_HOST + ":8080/nb4/OfficeFrame/orgcategories";
    String[] data = {"ltd", "self_employed", "jsc", "state_office", "state_enterprise", "international_company",
            "public_association", "city_Hall", "embassy", "educational_institution"};
    String[] dataEng = {"LTD", "Self employed", "JSC", "State office", "State enterprise", "International company",
            "Public association", "City Hall", "Embassy", "Educational institution"};
    String[] dataRus = {"ТОО", "Частный предприниматель", "АО", "Государственное ведомство", "РГП",
            "Зарубежная компания", "Общественное объединение", "Мэрия", "Посольство",
            "Образовательное учреждение"};
    String[] dataKaz = {"ТОО", "Частный предприниматель", "АО", "Государственное ведомство", "РГП",
            "Зарубежная компания", "Общественное объединение", "Акимат", "Посольство",
            "Образовательное учреждение"};

    @Test
    public void getOrgCategoryTest() {
        List<OrgCategory> entities = getEntities(BASE_SERVICE_URL);
        for (OrgCategory orgCategory : entities) {
            System.out.println(orgCategory.getLastModifiedDate());
        }
    }


    @Test
    public void addOrgCategories() throws Exception {

        for (int i = 0; i < data.length; i++) {
            OrgCategory existEntity = getEntity(BASE_SERVICE_URL + "/" + data[i]);

            OrgCategory newEntity = new OrgCategory();
            if (existEntity == null) {
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

            Response generalResp = save(BASE_SERVICE_URL, newEntity);
            int generalRespStatus = generalResp.getStatus();
            assertEquals(200, generalRespStatus);
            if (generalRespStatus == 200) {
                Outcome generalOutcome = generalResp.readEntity(Outcome.class);
                assertNotNull(generalOutcome);
            }
        }
    }


    public List<OrgCategory> getOrgCategories() {
        return getEntities(BASE_SERVICE_URL);

    }

}
