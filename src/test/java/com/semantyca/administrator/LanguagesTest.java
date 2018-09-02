package com.semantyca.administrator;

import com.semantyca.AbstractTest;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.init.ServerConst;
import com.semantyca.nb.modules.administrator.model.Language;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class LanguagesTest extends AbstractTest<Language> {
    private static String BASE_SERVICE_URL = "http://" + APPLICATION_HOST + ":8080/nb4/Administrator/languages";


    @Test
    public void addLanguages() throws Exception {
        System.out.println("POST");

        for (String lc : EnvConst.DEFAULT_LANGS) {
            Language language = ServerConst.getLanguage(LanguageCode.valueOf(lc));
            Language entity = getEntity(BASE_SERVICE_URL + "/" + language.getIdentifier());

            Language languageDto;
            if (entity == null) {
                languageDto = new Language();
                languageDto.setIdentifier(language.getIdentifier());
                languageDto.setCode(language.getCode());
                languageDto.setOn(true);
                languageDto.setDisposition(language.getDisposition());
                languageDto.setLocName(language.getLocName());
                languageDto.setTitle(language.getTitle());
            } else {
                languageDto = entity;
            }

            Response generalResp = save(BASE_SERVICE_URL, languageDto);
            int generalRespStatus = generalResp.getStatus();
            assertEquals(200, generalRespStatus);
            if (generalRespStatus == 200) {
                Outcome generalOutcome = generalResp.readEntity(Outcome.class);
                assertNotNull(generalOutcome);
            }
        }
    }
}
