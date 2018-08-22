package com.semantyca.officeframe.modules.reference.service;

import com.semantyca.nb.core.service.AbstractReferenceService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.reference.dao.ExplorationStatusDAO;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "explorationstatuses")
@RequestScoped
public class ExplorationStatusesResource extends AbstractReferenceService<ExplorationStatus> {

    @Inject
    private ExplorationStatusDAO dao;

    @Override
    protected ExplorationStatusDAO getDao() {
        return dao;
    }

}
