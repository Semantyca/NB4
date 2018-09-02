package com.semantyca.nb.core.service;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.logger.Lg;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractReferenceService<T extends IAppEntity> extends AbstractService<T> {

    @GET
    @Path("{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("identifier") String identifier) {
        Outcome outcome = new Outcome();
        T entity = null;
        if ("new".equalsIgnoreCase(identifier)) {
            entity = composeNew(session.getUser());
        } else {
            try {
                entity = getDao().findByIdentifier(identifier);
                outcome.setTitle("Status " + entity.getIdentifier());
            } catch (Exception e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        outcome.addPayload(entity);
        return Response.ok(outcome).build();
    }

    protected T composeNew(IUser user) {
        try {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T entityInst = entityClass.getConstructor().newInstance();
            entityInst.setAuthor(user.getId());
            entityInst.setTitle(entityClass.getTypeName());
            entityInst.setExistenceState(ExistenceState.NOT_SAVED);
            return entityInst;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            Lg.exception(e);
        }
        return null;
    }

}
