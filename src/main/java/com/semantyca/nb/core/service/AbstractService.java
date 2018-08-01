package com.semantyca.nb.core.service;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.IDAO;
import com.semantyca.nb.core.rest.RestProvider;
import com.semantyca.nb.core.rest.WebFormData;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.ui.action.ActionBar;
import com.semantyca.nb.ui.action.ConventionalActionFactory;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

public abstract class AbstractService<T extends IAppEntity> extends RestProvider {

    @Inject
    @Named("AuthenticatedUserSession")
    protected Session session;

    protected abstract IDAO<T, UUID> getDao();

    @PostConstruct
    public void init() {
       // Class entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
       // dao = DAOFactory.get(entityClass);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getViewPage() {
        IUser user = session.getUser();
        Outcome outcome = new Outcome();
        WebFormData params = getWebFormData();
        SortParams sortParams = params.getSortParams(SortParams.asc("login"));

        ViewPage vp = getDao().findViewPage(params.getPage(), session.getPageSize());
        //       vp.setResult(new UserToViewEntryConverter().convert(vp.getResult()));
        //       vp.setViewPageOptions(new ViewOptions().getUserOptions());

        if (user.isSuperUser()) {
            ConventionalActionFactory action = new ConventionalActionFactory();
            ActionBar actionBar = new ActionBar(session);
            actionBar.addAction(action.addNew);
            actionBar.addAction(action.deleteDocument);
            actionBar.addAction(action.refreshVew);
            outcome.addPayload(actionBar);
        }

        outcome.setTitle(vp.getMeta());
        outcome.addPayload(vp);

        return Response.ok(outcome).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        Outcome outcome = new Outcome();
        T entity = null;
        if ("new".equalsIgnoreCase(id)){
            entity = composeNew(session.getUser());
            outcome.setTitle("New");
        }else {
            try {
                entity = getDao().findById(id);
                outcome.setTitle(entity.getTitle());
            } catch (Exception e) {
                outcome.addPayload(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        outcome.addPayload(entity);
        return Response.ok(outcome).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(T dto) {
        Lg.info("test POST");
        Outcome outcome = new Outcome();
        outcome.setTitle(dto.getType());
        outcome.addPayload(getDao().add(dto));

        return Response.ok(outcome).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T dto) {
        Outcome outcome = new Outcome();
        outcome.setTitle(dto.getType());
        outcome.addPayload(getDao().update(dto));

        return Response.ok(outcome).build();
    }

    @POST
    @Path("uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(List<Attachment> attachments, @Context HttpServletRequest request) {
        for (Attachment attachment : attachments) {
            DataHandler handler = attachment.getDataHandler();
            try {
                InputStream stream = handler.getInputStream();
                MultivaluedMap<String, String> map = attachment.getHeaders();
                String temporaryFile = session.getTmpDir().getAbsolutePath() + File.separator + getFileName(map);
                System.out.println("fileName Here:" + temporaryFile);
                OutputStream out = new FileOutputStream(new File(temporaryFile));

                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = stream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                stream.close();
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Response.ok("file uploaded").build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String exactFileName = name[1].trim().replaceAll("\"", "");
                return exactFileName;
            }
        }
        return "unknown";
    }

    private T composeNew(IUser user){
        try {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T entityInst = entityClass.getConstructor().newInstance();
            entityInst.setAuthor(user.getId());
            entityInst.setTitle("");
            return entityInst;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            Lg.exception(e);
        }
        return null;
    }

}
