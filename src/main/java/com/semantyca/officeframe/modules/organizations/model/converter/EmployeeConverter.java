package com.semantyca.officeframe.modules.organizations.model.converter;

import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink.ELEntityConverter;
import com.semantyca.officeframe.modules.organizations.dao.EmployeeDAO;
import org.eclipse.persistence.sessions.Session;

import javax.inject.Inject;
import java.util.UUID;

public class EmployeeConverter extends ELEntityConverter {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmployeeDAO dao;


    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        if (dataValue != null) {
            return dao.findById((UUID) dataValue);
        }
        return null;

    }

}
