package com.semantyca.nb.core.service;

import com.semantyca.nb.core.control.AuthController;
import com.semantyca.nb.core.control.Credentials;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.env.Environment;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.util.TimeUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sessions")
@RequestScoped
public class SessionsResource {
    
    @Context
    HttpServletRequest request;

    @Inject
    AuthController authController;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credentials credentials) {
        Outcome outcome = new Outcome();
        try {
            Session userSession = authController.authenticate(credentials.getUserName(), credentials.getPwd());
            outcome.addPayload("session", userSession);
            if (userSession.isAuthenticated()) {
                return Response.ok(outcome).build();
            } else {
                return Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity(outcome).build();
            }

        } catch (Exception e) {
            return Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity(outcome).build();
        }
    }

    @GET
    public String ping() {
        return "PING Server " + EnvConst.SERVER_NAME + " " + EnvConst.SERVER_VERSION + " " + TimeUtil.dateTimeToStringSilently(Environment.startTime);
    }

    //better to use with Credentials dto. It is just in case
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@FormParam("userName") String userName, @FormParam("password") String password) {
        return  authenticateUser(new Credentials(userName, password));
    }


    @OPTIONS
    public Response getOptions() {
        return Response.ok().build();
    }


}
