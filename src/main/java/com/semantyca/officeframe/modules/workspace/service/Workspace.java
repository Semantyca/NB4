package com.semantyca.officeframe.modules.workspace.service;

import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.ui.workspace.Icon;
import com.semantyca.nb.ui.workspace.IconSet;
import com.semantyca.officeframe.modules.workspace.init.ModuleConst;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ModuleConst.BASE_URL)
public class Workspace {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDefault() {
        return getWorkspace();
    }

    @GET
    @Path("workspace")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkspace() {
        Outcome outcome = new Outcome();
        try {
            IconSet iconSet = new IconSet();
            iconSet.add(new Icon("1")).add(new Icon("2"));
            outcome.addPayload(iconSet);
            return Response.ok(outcome).build();

        } catch (Exception e) {
            return Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity(outcome).build();
        }
    }

}
