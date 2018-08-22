package com.semantyca.nb.modules.administrator.service;

import com.semantyca.nb.core.rest.RestProvider;
import com.semantyca.nb.core.rest.WebFormData;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.modules.administrator.dao.GroupDAO;
import com.semantyca.nb.modules.administrator.init.ModuleConst;
import com.semantyca.nb.ui.action.ActionBar;
import com.semantyca.nb.ui.action.ConventionalActionFactory;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ModuleConst.BASE_URL + "groups")
@RequestScoped
public class GroupsResource extends RestProvider {

    @Inject
    @Named("AuthenticatedUserSession")
    protected Session session;

    @Inject
    private GroupDAO dao;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getViewPage() {
        IUser user = session.getUser();
        Outcome outcome = new Outcome();
        WebFormData params = getWebFormData();
        SortParams sortParams = params.getSortParams(SortParams.asc("login"));

        ViewPage vp = dao.findViewPage(params.getPage(), session.getPageSize());
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

        outcome.setTitle("Unknown");
        outcome.setPayloadTitle("Unknown");
        outcome.addPayload(vp);

        return Response.ok(outcome).build();
    }





  /*  @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addForCXFImpl(User userDto) {
        return add(userDto);
    }



    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateForCXFImpl(User userDto) {
        return update(userDto);
    }*/
}
