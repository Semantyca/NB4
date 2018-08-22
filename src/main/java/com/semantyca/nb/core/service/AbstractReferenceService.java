package com.semantyca.nb.core.service;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.rest.outgoing.Outcome;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
