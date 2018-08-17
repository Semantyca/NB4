package com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

import java.sql.Types;

public abstract class ELEntityConverter implements Converter {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        if (objectValue != null) {
            return ((IAppEntity) objectValue).getId();
        }

        return null;

    }

    public abstract Object convertDataValueToObjectValue(Object dataValue, Session session);

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        DatabaseField field = mapping.getField();
        field.setSqlType(Types.OTHER);
        field.setTypeName("java.util.UUID");
        field.setColumnDefinition("uuid");
    }
}
