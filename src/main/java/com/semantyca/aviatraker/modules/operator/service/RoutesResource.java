package com.semantyca.aviatraker.modules.operator.service;

import com.semantyca.aviatraker.ApplicationConst;
import com.semantyca.aviatraker.modules.operator.dao.RouteDAO;
import com.semantyca.aviatraker.modules.operator.model.Route;
import com.semantyca.nb.core.service.AbstractService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "routes")
@RequestScoped
public class RoutesResource extends AbstractService<Route> {

    @Inject
    private RouteDAO dao;

    @Override
    protected RouteDAO getDao() {
        return dao;
    }
}
