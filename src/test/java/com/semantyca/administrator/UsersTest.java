package com.semantyca.administrator;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.user.constants.UserStatusCode;
import com.semantyca.nb.modules.administrator.model.User;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

public class UsersTest {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb/Administrator/users";
    private static List<Object> providers = new ArrayList<Object>();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        providers.add(new JacksonJsonProvider());

    }

    @Test
    public void getUsers() throws Exception {
        System.out.println("GET");
//        conn.setRequestMethod("GET");
        //      int responseCode = conn.getResponseCode();
        //     System.out.println("Response Code : " + responseCode);

        //    BufferedReader in = new BufferedReader(
        //            new InputStreamReader(conn.getInputStream()));
        //   String inputLine;
        StringBuffer response = new StringBuffer();

      /*  while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();*/
        System.out.println("Response: " + response);
    }

    @Test
    public void getUser() throws Exception {
        String id = "test1";
        Response resp = WebClient.create(BASE_SERVICE_URL + "/" + id, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        Outcome outcome = resp.readEntity(Outcome.class);

        Map entity = (Map) outcome.getPayload().get("user");
        assertNotNull(entity);
    }

    @Test
    public void addUsers() throws Exception {
        System.out.println("POST");

        String[] data = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"};
        for (int i = 0; i < data.length; i++) {
            boolean isNew = false;
            Response resp = WebClient.create(BASE_SERVICE_URL + "/" + data[i], providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(Response.class);

            Outcome outcome = resp.readEntity(Outcome.class);

            Map entity = null;
            try {
                entity = (Map) outcome.getPayload().get("user");
            } catch (Exception e) {

            }

            User user = new User();
            if (entity == null) {
                isNew = true;
            }
            user.setLogin(data[i]);
            user.setStatus(UserStatusCode.TEMPORARY);
            user.setDefaultLang(EnvConst.getDefaultLang());
            user.setEmail(data[i] + "@bla.com");
            user.setPassword("123");

            Response generalResp = null;
            if (isNew) {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .post(user, Response.class);
            } else {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .put(user, Response.class);
            }
            Outcome generalOutcome = generalResp.readEntity(Outcome.class);
            assertNotNull(generalOutcome);
        }

    }
}
