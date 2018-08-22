package com.semantyca.nb.core.service;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.IDAO;
import com.semantyca.nb.core.dataengine.jpa.IEntityWithAttachments;
import com.semantyca.nb.core.dataengine.jpa.model.EntityAttachment;
import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;
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
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
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
            ActionBar actionBar = new ActionBar();
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
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList() {
        Outcome outcome = new Outcome();
        List<T> list = getDao().findAll();
        outcome.addPayload(list);
        return Response.ok(outcome).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        Outcome outcome = new Outcome();
        T entity = null;
        if ("new".equalsIgnoreCase(id)) {
            entity = composeNew(session.getUser());
            outcome.setTitle("New");
        } else {
            try {
                entity = getDao().findById(id);
                outcome.setTitle(entity.getTitle());
            } catch (Exception e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        outcome.addPayload(entity);
        return Response.ok(outcome).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(T dto) {
        Outcome outcome = new Outcome();
        outcome.setTitle(dto.getType());
        if (dto.getExistenceState() == ExistenceState.NOT_SAVED) {
            dto.setExistenceState(ExistenceState.SAVED);
            return update(dto);
        } else {
            outcome.addPayload(getDao().add(dto));
        }

        return Response.ok(outcome).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T dto) {
        Outcome outcome = new Outcome();
        outcome.setTitle(dto.getType());
        if (dto.getExistenceState() != ExistenceState.SAVED) {
            dto.setExistenceState(ExistenceState.SAVED);
        }
        normalizeAttachments(dto);
        outcome.addPayload(getDao().update(dto));

        return Response.ok(outcome).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        T entity = getEntity(id);
        getDao().delete(entity);
        return Response.noContent().build();
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

        return Response.status(Response.Status.CREATED).entity(new Outcome().addPayload("uploaded")).build();
    }

    @GET
    @Path("{id}/openFile/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response openFile(@PathParam("id") String id, @PathParam("fileName") String fileName) {
        T entity = getEntity(id);
        try {
            File file = extractAttachment(entity, fileName);
            if (file != null) {
                String codedFileName = URLEncoder.encode(file.getName(), "UTF8");
                return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                        .header("Content-Disposition", "attachment; filename*=\"utf-8'" + codedFileName + "\"").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            Lg.exception(e);
            Outcome outcome = new Outcome();
            return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(outcome).build();
        }
    }

    public T getEntity(String id) {
        boolean isNew = "new".equals(id);
        if (isNew) {
            return composeNew(session.getUser());
        } else {
            return getDao().findById(UUID.fromString(id));
        }
    }

    protected T composeNew(IUser user) {
        try {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T entityInst = entityClass.getConstructor().newInstance();
            entityInst.setAuthor(user.getId());
            entityInst.setTitle("");
            entityInst.setExistenceState(ExistenceState.NOT_SAVED);
            return entityInst;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            Lg.exception(e);
        }
        return null;
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

    protected File extractAttachment(IAppEntity entity, String fileName) {
        if (IEntityWithAttachments.class.isAssignableFrom(entity.getClass())) {
            IEntityWithAttachments entityWithAttachments = (IEntityWithAttachments) entity;
            for (EntityAttachment entityAttachment : entityWithAttachments.getFiles()) {
                if (entityAttachment.getFileName().equals(fileName)) {
                    String temporaryFile = session.getTmpDir().getAbsolutePath() + File.separator + fileName;
                    File tf = new File(temporaryFile);
                    if (writeToFile(tf.getAbsolutePath(), entityAttachment.getFile())) {
                        return tf;
                    }
                }
            }
        }
        return null;
    }

    private boolean writeToFile(String fileName, byte[] content) {
        FileOutputStream stream = null;
        try {
            if (content == null) {
                return false;
            }
            stream = new FileOutputStream(fileName);
            stream.write(content);
            return true;
        } catch (FileNotFoundException e) {
            Lg.error(e);
        } catch (IOException e) {
            Lg.error(e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {

            }
        }
        return false;
    }

    private void normalizeAttachments(T entity) {
        if (IEntityWithAttachments.class.isAssignableFrom(entity.getClass())) {
            IEntityWithAttachments entityWithAttachments = (IEntityWithAttachments) entity;
            for (EntityAttachment entry : entityWithAttachments.getFiles()) {
                if (entry.getFile() == null) {
                    EntityAttachment attachment = entry;
                    File temporaryFile = new File(session.getTmpDir().getAbsolutePath() + File.separator + attachment.getFileName());
                    try {
                        byte[] fileContent = Files.readAllBytes(temporaryFile.toPath());
                        attachment.setFile(fileContent);
                        List<EntityAttachment> attachmentMap = new ArrayList<>();
                        attachmentMap.add(attachment);
                        entityWithAttachments.setFiles(attachmentMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
