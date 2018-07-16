package com.semantyca.nb.modules.administrator.service;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.modules.administrator.init.ModuleConst;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ModuleConst.BASE_URL)
public class Login {

    @GET
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        Outcome outcome = new Outcome();
        outcome.addPayload("Server " + EnvConst.SERVER_NAME + " " + EnvConst.SERVER_VERSION);
        return Response.ok(outcome).build();

    }

}
