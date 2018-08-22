package com.semantyca.officeframe.modules.organizations.model.converter;

import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink.ELEntityConverter;
import com.semantyca.officeframe.modules.organizations.dao.EmployeeDAO;
import com.semantyca.officeframe.modules.organizations.model.Employee;
import org.eclipse.persistence.sessions.Session;

import java.util.UUID;


public class EmployeeConverter extends ELEntityConverter {
    private static EmployeeDAO dao;

    @Override
    public Employee convertDataValueToObjectValue(Object dataValue, Session session) {
        if (dataValue != null) {
            if (dao == null) {
                dao = javax.enterprise.inject.spi.CDI.current().select(EmployeeDAO.class).get();
            }
            Employee employee = dao.findById((UUID) dataValue);
            return employee;
        }
        return null;

    }

}
