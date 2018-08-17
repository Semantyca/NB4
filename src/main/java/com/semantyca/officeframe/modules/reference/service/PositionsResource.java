package com.semantyca.officeframe.modules.reference.service;

import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.reference.dao.PositionDAO;
import com.semantyca.officeframe.modules.reference.model.Position;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "positions")
@RequestScoped
public class PositionsResource extends AbstractService<Position> {

    @Inject
    private PositionDAO dao;

    @Override
    protected PositionDAO getDao() {
        return dao;
    }


}
