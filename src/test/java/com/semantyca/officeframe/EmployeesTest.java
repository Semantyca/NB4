package com.semantyca.officeframe;

import com.semantyca.AbstractTest;
import com.semantyca.administrator.UsersTest;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.model.User;
import com.semantyca.nb.util.ListUtil;
import com.semantyca.nb.util.NumberUtil;
import com.semantyca.officeframe.modules.organizations.model.Employee;
import com.semantyca.officeframe.modules.organizations.model.Organization;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class EmployeesTest extends AbstractTest<Employee> {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/OfficeFrame/employees";
    private String[] data = {"Витвицкий Руслан", "Nuno Zimas"};
    private String[] dataEng = {"Vitvitsky Ruslan", "Nuno Zimas"};
    private String[] dataRus = {"Витвицкий Руслан", "Нуно Зимаш"};
    private String[] dataKaz = {"Витвицкий Руслан", "Нуно Зимаш"};


    @Test
    public void addEmployees() {

        for (int i = 0; i < data.length; i++) {
            Employee existEntity = getEntity(BASE_SERVICE_URL + "/" + data[i]);
            Employee newEntity = new Employee();
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
                newEntity.setUser((User) ListUtil.getRndElement(new UsersTest().getUsers()));
                Map files = new HashMap();

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

    public List<Employee> getEmployees() {
        return getEntities(BASE_SERVICE_URL);
    }

}
