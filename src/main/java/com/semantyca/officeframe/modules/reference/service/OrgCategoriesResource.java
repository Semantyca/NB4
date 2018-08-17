package com.semantyca.officeframe.modules.reference.service;

import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.reference.dao.OrgCategoryDAO;
import com.semantyca.officeframe.modules.reference.model.OrgCategory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "orgcategories")
@RequestScoped
public class OrgCategoriesResource extends AbstractService<OrgCategory> {

    @Inject
    private OrgCategoryDAO dao;

    @Override
    protected OrgCategoryDAO getDao() {
        return dao;
    }


}
