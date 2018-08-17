package com.semantyca.skyra.modules.operator.service;

import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.skyra.ApplicationConst;
import com.semantyca.skyra.modules.operator.dao.ExplorationDAO;
import com.semantyca.skyra.modules.operator.model.Exploration;

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
