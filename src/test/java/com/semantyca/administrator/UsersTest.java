package com.semantyca.administrator;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.user.constants.UserStatusCode;
import com.semantyca.nb.modules.administrator.model.User;
import com.semantyca.nb.util.StringUtil;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.ConfigurableJohnzonProvider;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class UsersTest {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/Administrator/users";
    private static List<Object> providers = new ArrayList<Object>();

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
         providers.add(new ConfigurableJohnzonProvider());
    }

    @Test
    public void getUsers() throws Exception {
        System.out.println("GET");
        Response resp = WebClient.create(BASE_SERVICE_URL, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        int status = resp.getStatus();
        assertEquals(200, status);
        if (status == 200) {
            Outcome outcome = resp.readEntity(Outcome.class);

            Map entity = (Map) outcome.getPayload().get("viewpage");
            assertNotNull(entity);
            System.out.println("Response: " + entity);
        }
    }

    @Test
    public void getUser() throws Exception {
        String id = "test1";
        Response resp = WebClient.create(BASE_SERVICE_URL + "/" + id, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        Outcome outcome = resp.readEntity(Outcome.class);

        Map entity = (Map) outcome.getPayload().get(Outcome.ENTITY_PAYLOAD);
        assertNotNull(entity);
    }

    @Test
    public void addUsers() throws Exception {

        List<String> data = new ArrayList();

        for (int i = 0; i < 100; i++) {
            data.add(StringUtil.getRndText());
        }

        for (String login : data) {
            boolean isNew = false;
            Response resp = WebClient.create(BASE_SERVICE_URL + "/" + login, providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(Response.class);

            Map entity = null;
            int status = resp.getStatus();
            if (status == 200) {
                Outcome outcome = resp.readEntity(Outcome.class);
                try {
                    entity = (Map) outcome.getPayload().get("user");
                } catch (Exception e) {

                }
            }

            User user = null;
            if (entity == null) {
                isNew = true;
                user = new User();
            }
            user.setLogin(login);
            user.setStatus(UserStatusCode.TEMPORARY);
            user.setDefaultLang(EnvConst.getDefaultLang());
            user.setEmail(login + "@bla.com");
            user.setPassword("123");

            Response generalResp = null;
            if (isNew) {
                System.out.println("POST");
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .type(MediaType.APPLICATION_JSON)
                        .post(user, Response.class);
            } else {
                System.out.println("PUT");
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .put(user, Response.class);
            }
            int generalRespStatus = generalResp.getStatus();
            assertEquals(user.getLogin(), 200, generalRespStatus);
            if (generalRespStatus == 200) {
                Outcome generalOutcome = generalResp.readEntity(Outcome.class);
                assertNotNull(generalOutcome);
            }
        }
    }
}
