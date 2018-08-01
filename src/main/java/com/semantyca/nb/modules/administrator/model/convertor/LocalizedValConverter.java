package com.semantyca.nb.modules.administrator.model.convertor;

import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.logger.Lg;
import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.postgresql.util.PGobject;

import javax.json.stream.JsonGenerationException;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = true)
public class LocalizedValConverter implements AttributeConverter<Map<LanguageCode, String>, PGobject> {
    final Mapper mapper = new MapperBuilder().build();

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
    }
}