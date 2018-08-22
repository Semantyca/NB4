package com.semantyca.skyra.modules.operator.service;

import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;
import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.officeframe.modules.reference.dao.ExplorationStatusDAO;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
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
        dao.add(entityInst);
        return entityInst;
    }

}
