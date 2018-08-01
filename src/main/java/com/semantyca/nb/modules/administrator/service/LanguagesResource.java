package com.semantyca.nb.modules.administrator.service;

import com.semantyca.nb.core.rest.WebFormData;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.modules.administrator.dao.LanguageDAO;
import com.semantyca.nb.modules.administrator.init.ModuleConst;
import com.semantyca.nb.modules.administrator.model.Language;
import com.semantyca.nb.ui.action.ActionBar;
import com.semantyca.nb.ui.action.ConventionalActionFactory;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ModuleConst.BASE_URL + "languages")
@RequestScoped
public class LanguagesResource extends AbstractService<Language> {

    @Inject
    private LanguageDAO dao;

    @Override
    protected LanguageDAO getDao() {
        return dao;
    }

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
            ActionBar actionBar = new ActionBar(session);
            actionBar.addAction(action.addNew);
            actionBar.addAction(action.deleteDocument);
            actionBar.addAction(action.refreshVew);
            outcome.addPayload(actionBar);
        }

        outcome.setTitle("Languages");
        outcome.setPayloadTitle("Languages");
        outcome.addPayload(vp);

        return Response.ok(outcome).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        Outcome outcome = new Outcome();
        Language entity = null;
        if ("new".equalsIgnoreCase(id)){
            entity = new Language();
            outcome.setTitle("New language");
        }else {
            try {
                entity = dao.findById(id);
                if (entity != null) {
                    outcome.setTitle("Language " + entity.getIdentifier());
                }else{
                    return Response.status(Response.Status.NOT_FOUND).build();
                }
            } catch (Exception e) {
                outcome.addPayload(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        outcome.addPayload(entity);
        return Response.ok(outcome).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Language dto) {
        Lg.info("test POST");
        Outcome outcome = new Outcome();
        outcome.setTitle("Languages");
        outcome.setPayloadTitle("Languages");
        outcome.addPayload(dao.add(dto));

        return Response.ok(outcome).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Language dto) {
        Outcome outcome = new Outcome();

        outcome.setTitle("Languages");
        outcome.setPayloadTitle("Languages");
        outcome.addPayload(dao.update(dto));

        return Response.ok(outcome).build();
    }



}
