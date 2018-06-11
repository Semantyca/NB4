package com.semantyca.nb.administrator.boundary;

import com.semantyca.nb.administrator.entity.User;
import com.semantyca.nb.administrator.init.AdmModuleConst;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.UserSession;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path(AdmModuleConst.BASE_URL + "users")
public class UsersResource {


    @Inject
    @Named("AuthenticatedUserSession")
    UserSession session;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getViewPage() {
        User user = session.getUser();
        Outcome outcome = new Outcome();
      /*  WebFormData params = getWebFormData();
        SortParams sortParams = params.getSortParams(SortParams.asc("login"));


        UserDAO dao = new UserDAO(session);
        ViewPage<User> vp = dao.findAll(sortParams, "", params.getPage(), session.getPageSize());
        vp.setResult(new UserToViewEntryConverter().convert(vp.getResult()));
        vp.setViewPageOptions(new ViewOptions().getUserOptions());

        if (user.isSuperUser()) {
            ConventionalActionFactory action = new ConventionalActionFactory();
            ActionBar actionBar = new ActionBar(session);
            actionBar.addAction(action.addNew);
            actionBar.addAction(action.deleteDocument);
            actionBar.addAction(action.refreshVew);
            outcome.addPayload(actionBar);
        }

        outcome.setTitle("Users");
        outcome.setPayloadTitle("Users");
        outcome.addPayload(vp);*/

        return Response.ok(outcome).build();
    }
}
