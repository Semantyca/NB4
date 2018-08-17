package com.semantyca.officeframe.modules.reference.service;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.reference.dao.ExplorationStatusDAO;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ApplicationConst.BASE_URL + "explorationstatuses")
@RequestScoped
public class ExplorationStatusesResource extends AbstractService<ExplorationStatus> {

    @Inject
    private ExplorationStatusDAO dao;

    @Override
    protected ExplorationStatusDAO getDao() {
        return dao;
    }

    @GET
    @Path("{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("identifier") String identifier) {
        Outcome outcome = new Outcome();
        ExplorationStatus entity = null;
        if ("new".equalsIgnoreCase(identifier)) {
            entity = new ExplorationStatus();
        } else {
            try {
                entity = dao.findByIdentifier(identifier);
                outcome.setTitle("Status " + entity.getIdentifier());
            } catch (Exception e) {
                outcome.addPayload(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        outcome.addPayload(entity);
        return Response.ok(outcome).build();
    }
}
