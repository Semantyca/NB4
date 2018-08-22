package com.semantyca.officeframe.modules.organizations.service;

import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.organizations.dao.DepartmentDAO;
import com.semantyca.officeframe.modules.organizations.model.Department;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "departments")
@RequestScoped
public class DepartmentsResource extends AbstractService<Department> {

    @Inject
    private DepartmentDAO dao;

    @Override
    protected DepartmentDAO getDao() {
        return dao;
    }


}
