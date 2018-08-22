package com.semantyca.nb.core.dataengine.jpa.model.convertor.db;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.logger.Lg;
import org.postgresql.util.PGobject;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LocalizedValConverter implements AttributeConverter<Map<LanguageCode, String>, PGobject> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PGobject convertToDatabaseColumn(Map<LanguageCode, String> val) {
        try {
            PGobject po = new PGobject();
            po.setType("jsonb");
            po.setValue(mapper.writeValueAsString(val));
            return po;
        } catch (SQLException e) {
            Lg.exception(e);
            return null;
        } catch (com.fasterxml.jackson.core.JsonGenerationException e) {
            Lg.exception(e);
            return null;
        } catch (JsonMappingException e) {
            Lg.exception(e);
            return null;
        } catch (IOException e) {
            Lg.exception(e);
            return null;
        }
    }

    @Override
    public Map<LanguageCode, String> convertToEntityAttribute(PGobject po) {
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(Map.class, LanguageCode.class, String.class);
            return mapper.readValue(po.getValue(), mapType);
        } catch (Exception e) {
            // Server.logger.errorLogEntry(e.toString());
            return new HashMap<LanguageCode, String>();
        }
    }

  /*  final Mapper mapper = new MapperBuilder().build();

    @Override
    public PGobject convertToDatabaseColumn(Map<LanguageCode, String> val) {
        try {
            PGobject po = new PGobject();
            po.setType("jsonb");
            po.setValue(mapper.writeObjectAsString(val));
            return po;
        } catch (SQLException e) {
            Lg.exception(e);
            return null;
        } catch (JsonGenerationException e) {
            Lg.exception(e);
            return null;
        }
    }

    @Override
    public Map<LanguageCode, String> convertToEntityAttribute(PGobject po) {
        try {
            return mapper.readObject(po.getValue(),Map.class);
        } catch (Exception e) {
            // Server.logger.errorLogEntry(e.toString());
            return new HashMap<LanguageCode, String>();
        }
    }*/
}