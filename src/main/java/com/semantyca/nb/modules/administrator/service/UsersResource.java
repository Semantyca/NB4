package com.semantyca.nb.modules.administrator.service;

import com.semantyca.nb.core.rest.RestProvider;
import com.semantyca.nb.core.rest.WebFormData;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.modules.administrator.dao.UserDAO;
import com.semantyca.nb.modules.administrator.init.ModuleConst;
import com.semantyca.nb.modules.administrator.model.User;
import com.semantyca.nb.ui.action.ActionBar;
import com.semantyca.nb.ui.action.ConventionalActionFactory;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;
import com.semantyca.nb.util.StringUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ModuleConst.BASE_URL + "users")
@RequestScoped
public class UsersResource extends RestProvider {

    @Inject
    @Named("AuthenticatedUserSession")
    protected Session session;

    @Inject
    private UserDAO dao;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getViewPage() {
        IUser user = session.getUser();
        Outcome outcome = new Outcome();
        WebFormData params = getWebFormData();
        SortParams sortParams = params.getSortParams(SortParams.asc("login"));
        session.setPageSize(params.getPageSize());
        ViewPage vp = dao.findViewPage(params.getPage(), session.getPageSize());
 //       vp.setResult(new UserToViewEntryConverter().convert(vp.getResult()));
 //       vp.setViewPageOptions(new ViewOptions().getUserOptions());

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
        outcome.addPayload(vp);

         return Response.ok(outcome).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        Outcome outcome = new Outcome();
        User user = null;
        if ("new".equalsIgnoreCase(id)){
            user = new User();
            outcome.setTitle("New user");
        }else {
            try {
                user = dao.findById(id);
                outcome.setTitle("User " + user.getLogin());
            } catch (Exception e) {
                outcome.addPayload(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        outcome.addPayload(user);
        return Response.ok(outcome).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User userDto) {
        Lg.info("test POST");
        IUser user = session.getUser();
        Outcome outcome = new Outcome();

        if (userDto.getPassword() == null){
            userDto.setPassword(StringUtil.getRndText());
        }

        outcome.setTitle("Users");
        outcome.setPayloadTitle("Users");
        outcome.addPayload(dao.add(userDto));

        return Response.ok(outcome).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(User userDto) {
        Lg.info("test PUT");
        IUser user = session.getUser();
        Outcome outcome = new Outcome();

        if (userDto.getPassword() == null){
            userDto.setPassword(StringUtil.getRndText());
        }
        outcome.setTitle("Users");
        outcome.setPayloadTitle("Users");
        outcome.addPayload(dao.update(userDto));

        return Response.ok(outcome).build();
    }



  //  @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addForCXFImpl(User userDto) {
        return add(userDto);
    }

/*

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateForCXFImpl(User userDto) {
        return update(userDto);
    }*/
}
