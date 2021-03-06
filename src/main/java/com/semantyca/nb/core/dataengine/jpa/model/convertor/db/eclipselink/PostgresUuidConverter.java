package com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink;

import javax.persistence.AttributeConverter;
import java.sql.SQLException;
import java.util.UUID;

@javax.persistence.Converter(autoApply = false)
public class PostgresUuidConverter implements AttributeConverter<UUID, Object> {

    @Override
    public Object convertToDatabaseColumn(UUID uuid) {
        PostgresUuid object = new PostgresUuid();
        object.setType("uuid");
        try {
            if (uuid == null) {
                object.setValue(null);
            } else {
                object.setValue(uuid.toString());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error when creating Postgres uuid", e);
        }
        return object;
    }

    @Override
    public UUID convertToEntityAttribute(Object dbData) {
        if (dbData instanceof String) {
            return UUID.fromString(dbData.toString());
        } else {
            return (UUID) dbData;
        }
    }

}