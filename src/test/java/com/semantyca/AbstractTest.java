package com.semantyca;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.semantyca.nb.core.dataengine.jpa.ISimpleAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.junit.BeforeClass;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractTest<T extends ISimpleAppEntity> {
    //protected static String APPLICATION_HOST = "semantyca.com";
    protected static String APPLICATION_HOST = "localhost";
    protected static List<Object> providers = new ArrayList<Object>();
    private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeClass
    public static void setUpBeforeClass() {
        providers.add(new JacksonJaxbJsonProvider());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public T getEntity(String endPoint) {
        Response resp = WebClient.create(endPoint, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
        int status = resp.getStatus();
        T existEntity = null;
        if (status == 200) {
            Outcome outcome = resp.readEntity(Outcome.class);
            try {
                Map entity = (Map) outcome.getPayload().get(Outcome.ENTITY_PAYLOAD);
                existEntity = mapper.convertValue(entity, entityClass);
            } catch (Exception e) {

            }
        } else if (status == 404) {
            System.out.println(endPoint + " > 404");
        }
        return existEntity;

    }

    public List<T> getEntities(String endPoint) {
        List<T> entities = new ArrayList<>();
        Response resp = WebClient.create(endPoint, providers)
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
        int status = resp.getStatus();
        if (status == 200) {
            //System.out.println(resp.readEntity(String.class));
            Outcome outcome = resp.readEntity(Outcome.class);
            try {
                Map vp = (Map) outcome.getPayload().get(Outcome.VIEW_PAGE_PAYLOAD);
                List<Map> list = (List<Map>) vp.get("result");
                for (Map element : list) {
                    T entity = mapper.convertValue(element, entityClass);
                    entities.add(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entities;

    }

    protected Response save(String endPoint, T newEntity) {
        System.out.print("endPoint = [" + endPoint + "] ");
        if (newEntity.getExistenceState() == ExistenceState.NOT_SAVED || newEntity.getId() == null) {
            System.out.println("POST");
            return WebClient.create(endPoint, providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.APPLICATION_JSON)
                    .post(newEntity, Response.class);
        } else {
            System.out.println("PUT");
            return WebClient.create(endPoint, providers)
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.APPLICATION_JSON)
                    .put(newEntity, Response.class);
        }
    }

    protected Response uploadFile(String endPoint, File fileToUpload) throws IOException {
        endPoint = endPoint + "/uploadFile";
        System.out.print("endPoint = [" + endPoint + "] ");
        System.out.println("POST (uploadFile)");
        ContentDisposition cd = new ContentDisposition("attachment;filename=" + fileToUpload.getName());
        Attachment att = new Attachment("root", new FileInputStream(fileToUpload), cd);

        return WebClient.create(endPoint, providers)
                .type(MediaType.MULTIPART_FORM_DATA)
                .post(new MultipartBody(att), Response.class);

    }
}
