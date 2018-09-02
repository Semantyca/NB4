package com.semantyca.skyra.modules.operator.service;

import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;
import com.semantyca.nb.core.rest.outgoing.Outcome;
import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.officeframe.modules.reference.dao.ExplorationStatusDAO;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import com.semantyca.skyra.ApplicationConst;
import com.semantyca.skyra.modules.operator.dao.ExplorationDAO;
import com.semantyca.skyra.modules.operator.model.Exploration;
import com.semantyca.skyra.modules.operator.model.embedded.PointCoordiantes;
import com.semantyca.skyra.modules.operator.other.KMLParser;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path(ApplicationConst.BASE_URL + "explorations")
@RequestScoped
public class ExplorationsResource extends AbstractService<Exploration> {

    @Inject
    private ExplorationDAO dao;

    @Inject
    private ExplorationStatusDAO explorationStatusDAO;

    @Override
    protected ExplorationDAO getDao() {
        return dao;
    }

    protected Exploration composeNew(IUser user) {
        Exploration entityInst = new Exploration();
        entityInst.setAuthor(user.getId());
        entityInst.setTitle("");
        ExplorationStatus status = explorationStatusDAO.findByIdentifier("planned");
        entityInst.setStatus(status);
        entityInst.setExistenceState(ExistenceState.NOT_SAVED);
        entityInst.setStartTime(entityInst.getRegDate());
        dao.add(entityInst);
        return entityInst;
    }


    @POST
    @Path("uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(List<Attachment> attachments, @Context HttpServletRequest request) {
        List<PointCoordiantes> coordinates = new ArrayList<>();
        Outcome outcome = attachmentRequestHandler(attachments);
        KMLParser parser = new KMLParser();
        coordinates.addAll(parser.process(outcome.temporaryFileName));
        outcome.addPayload(coordinates);
        return Response.status(Response.Status.CREATED).entity(outcome).build();
    }

    @GET
    @Path("action/getCoordinates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExploration() {
        List<Exploration> coordinates = new ArrayList<>();
        List<Exploration> list = getDao().findAll();
        for (Exploration exploration : list) {
            if (exploration.getFlightPath().size() > 0) {
                coordinates.add(exploration);
            }
        }
        Outcome outcome = new Outcome();
        return Response.status(Response.Status.CREATED).entity(outcome.addPayload(coordinates)).build();
    }


}
