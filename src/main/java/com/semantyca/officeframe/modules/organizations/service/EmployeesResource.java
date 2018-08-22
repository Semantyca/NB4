package com.semantyca.officeframe.modules.organizations.service;

import com.semantyca.nb.core.service.AbstractService;
import com.semantyca.officeframe.ApplicationConst;
import com.semantyca.officeframe.modules.organizations.dao.EmployeeDAO;
import com.semantyca.officeframe.modules.organizations.model.Employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;


@Path(ApplicationConst.BASE_URL + "employees")
@RequestScoped
public class EmployeesResource extends AbstractService<Employee> {

    @Inject
    private EmployeeDAO dao;

    @Override
    protected EmployeeDAO getDao() {
        return dao;
    }


}
