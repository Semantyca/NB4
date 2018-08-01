package com.semantyca.aviatraker.modules.operator.dao;

import com.semantyca.aviatraker.modules.operator.model.Route;
import com.semantyca.nb.core.dataengine.jpa.dao.DAO;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class RouteDAO extends DAO<Route, UUID> {


}
