package com.semantyca.officeframe.modules.organizations.model.converter;

import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink.ELEntityConverter;
import com.semantyca.officeframe.modules.organizations.dao.DepartmentDAO;
import org.eclipse.persistence.sessions.Session;

import javax.inject.Inject;
import java.util.UUID;

public class DepartmentConverter extends ELEntityConverter {
    private static final long serialVersionUID = 1L;

    @Inject
    private DepartmentDAO dao;


    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        if (dataValue != null) {
            return dao.findById((UUID) dataValue);
        }
        return null;

    }

}
