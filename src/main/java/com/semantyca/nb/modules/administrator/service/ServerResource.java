package com.semantyca.nb.modules.administrator.service;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.nb.modules.administrator.init.ModuleConst;
import com.semantyca.nb.modules.administrator.model.Server;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ModuleConst.BASE_URL + "server")
@RequestScoped
public class ServerResource extends AbstractService {

    @Inject
    @Named("AuthenticatedUserSession")
    protected Session session;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getServerDetails() {
        Outcome outcome = new Outcome();
        outcome.setTitle("Server");
        outcome.setPayloadTitle("Server");
        Server server = new Server();
        outcome.addPayload(server);

        return Response.ok(outcome).build();
    }
}
