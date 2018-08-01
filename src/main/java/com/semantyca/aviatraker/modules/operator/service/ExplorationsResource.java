package com.semantyca.aviatraker.modules.operator.service;

import com.semantyca.aviatraker.ApplicationConst;
import com.semantyca.aviatraker.modules.operator.dao.ExplorationDAO;
import com.semantyca.aviatraker.modules.operator.model.Exploration;
import com.semantyca.nb.core.service.AbstractService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "explorations")
@RequestScoped
public class ExplorationsResource extends AbstractService<Exploration> {

    @Inject
    private ExplorationDAO dao;

    @Override
    protected ExplorationDAO getDao() {
        return dao;
    }
}
