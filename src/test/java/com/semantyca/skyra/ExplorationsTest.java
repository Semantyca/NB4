package com.semantyca.skyra;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.dataengine.jpa.model.EntityAttachment;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.util.FileUtil;
import com.semantyca.nb.util.ListUtil;
import com.semantyca.nb.util.StringUtil;
import com.semantyca.nb.util.TimeUtil;
import com.semantyca.officeframe.EmployeesTest;
import com.semantyca.officeframe.ExplorationStatusesTest;
import com.semantyca.officeframe.modules.organizations.model.Employee;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import com.semantyca.skyra.modules.operator.model.Exploration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExplorationsTest extends AbstractTest<Exploration> {
    private static String BASE_SERVICE_URL = "http://" + APPLICATION_HOST + ":8080/nb4/Skyra/explorations";
    private static String FILES_PLAYGROUND = "/home/aida/Downloads";

    @Test
    public void getExplorationsTest() {
        List<Exploration> entities = getEntities(BASE_SERVICE_URL);
        for (Exploration exploration : entities) {
            System.out.println(exploration);
        }
    }

    @Test
    public void addTestExplorations() throws Exception {
        List<String> data = new ArrayList();

        for (int i = 0; i < 3; i++) {
            data.add("mock identifier " + StringUtil.getRndText("abc", 3));
        }

        for (String u: data) {
            Exploration entity = getEntity(BASE_SERVICE_URL + "/" + u);
            Exploration newEntity;
            if (entity == null) {
                newEntity = getEntity(BASE_SERVICE_URL + "/new");
                List<ExplorationStatus> statuses = new ExplorationStatusesTest().getExplorationStatuses(providers);
                ExplorationStatus explorationStatus = (ExplorationStatus) ListUtil.getRndElement(statuses);
                newEntity.setStatus(explorationStatus);
                newEntity.setStartTime(TimeUtil.getRndDateTime());
                List<Employee> employees = new EmployeesTest().getEmployees();
                newEntity.setPilot1((Employee) ListUtil.getRndElement(employees));
                newEntity.setPilot2((Employee) ListUtil.getRndElement(employees));
                newEntity.setTitle(u);
                //    File file = FileUtil.getRndFile(FILES_PLAYGROUND);
                //    assertTrue(file.exists());
                EntityAttachment entityAttachment = new EntityAttachment();
                entityAttachment.setComment("comment example");
                //       entityAttachment.setFileName(file.getName());
                //      System.out.println(file.getAbsolutePath());
                //      uploadFile(BASE_SERVICE_URL, file);
                //     byte[] fileContent = Files.readAllBytes(file.toPath());
                //     entityAttachment.setFile(fileContent);
                //      List<EntityAttachment> attachmentMap = new ArrayList<>();
                //     attachmentMap.add(entityAttachment);
                //    newEntity.setFiles(attachmentMap);
                //newEntity.setPointCoordiantes(new KMLParser().process("/home/aida/Downloads/map/кар3.kml"));
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
    public void deleteExplorations() throws Exception {
        List<Exploration> entities = getEntities(BASE_SERVICE_URL);

        for (Exploration u : entities) {
            Response generalResp = null;
            generalResp = WebClient.create(BASE_SERVICE_URL + "/" + u.getId(), providers)
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
    public void uploadRandomFile() throws Exception {
        File file = FileUtil.getRndFile(FILES_PLAYGROUND);
        assertTrue(file.exists());
        System.out.println(file.getAbsolutePath());
        uploadFile(BASE_SERVICE_URL, file);
    }


    public List<Exploration> getOrganizations() {
        return getEntities(BASE_SERVICE_URL);

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class dto = Class.forName("com.semantyca.skyra.modules.operator.model.Exploration");
        for (Method method : dto.getMethods()) {


            if (method.getName().equals("getFiles") && method.getReturnType().getSimpleName().equals("Map")) {
                System.out.println(method.getReturnType().getSimpleName() + " " + method.getName());
            }
        }
    }

}
