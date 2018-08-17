package com.semantyca.officeframe.modules.organizations.service;

import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.organizations.dao.OrganizationDAO;
import com.semantyca.officeframe.modules.organizations.model.Organization;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "organizations")
@RequestScoped
public class OrganizationsResource extends AbstractService<Organization> {

    @Inject
    private OrganizationDAO dao;

    @Override
    protected OrganizationDAO getDao() {
        return dao;
    }


}
