package com.semantyca.nb.core.boundary;

import com.semantyca.administrator.entity.User;
import com.semantyca.nb.core.env.EnvConst;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;

@Path("sessions")
@RequestScoped
public class SessionsResource {
    
    @Context
    HttpServletRequest request;

    @Inject
    SessionProvider provider;

    private User user;

    @GET
    public String ping() {
        return "Server " + EnvConst.SERVER_NAME + " " + EnvConst.SERVER_VERSION;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@FormParam("username") String userName, @FormParam("password") String password) {
      //  Lg.info("Attempting login=" + userName);
        Principal principal = request.getUserPrincipal();
        user = provider.findUserById(principal.getName());
  //      Lg.info("Authentication done for user: " + principal.getName());
        if (request.isUserInRole("users")) {

        } else {

        }
        return Response.status(200).build();
    }
}
