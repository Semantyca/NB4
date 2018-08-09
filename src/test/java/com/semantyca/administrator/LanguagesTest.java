package com.semantyca.administrator;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.init.ServerConst;
import com.semantyca.nb.modules.administrator.model.Language;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.ConfigurableJohnzonProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class LanguagesTest {
    private static String BASE_SERVICE_URL = "http://localhost:8080/nb4/Administrator/languages";
    private static List<Object> providers = new ArrayList<Object>();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        providers.add(new ConfigurableJohnzonProvider());

    }


    @Test
    public void addLanguages() throws Exception {
        System.out.println("POST");

        for (String lc : EnvConst.DEFAULT_LANGS) {
            Language language = ServerConst.getLanguage(LanguageCode.valueOf(lc));
            boolean isNew = false;
            Response resp = WebClient.create(BASE_SERVICE_URL + "/" + language.getIdentifier(), providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(Response.class);

            Map entity = null;
            int status = resp.getStatus();
            if (status == 200) {
                Outcome outcome = resp.readEntity(Outcome.class);
                try {
                    entity = (Map) outcome.getPayload().get(Outcome.ENTITY_PAYLOAD);
                } catch (Exception e) {

                }
            }

            Language languageDto = new Language();
            if (entity == null) {
                isNew = true;
            }
            languageDto.setIdentifier(language.getIdentifier());
            languageDto.setCode(language.getCode());
            languageDto.setOn(true);
            languageDto.setDisposition(language.getDisposition());
            languageDto.setLocName(language.getLocName());
            languageDto.setTitle(language.getTitle());
            Response generalResp = null;
            if (isNew) {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .type(MediaType.APPLICATION_JSON)
                        .post(languageDto, Response.class);
            } else {
                generalResp = WebClient.create(BASE_SERVICE_URL, providers)
                        .accept(MediaType.APPLICATION_JSON)
                        .put(languageDto, Response.class);
            }

            int generalRespStatus = generalResp.getStatus();
            assertEquals(200, generalRespStatus);
            if (generalRespStatus == 200) {
                Outcome generalOutcome = generalResp.readEntity(Outcome.class);
                assertNotNull(generalOutcome);
            }
        }
    }
}
