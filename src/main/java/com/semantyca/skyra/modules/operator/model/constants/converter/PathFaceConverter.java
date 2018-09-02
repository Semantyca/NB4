package com.semantyca.skyra.modules.operator.model.constants.converter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semantyca.nb.logger.Lg;
import com.semantyca.skyra.modules.operator.model.embedded.PathFaceOptions;
import org.postgresql.util.PGobject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.sql.SQLException;


@Converter
public class PathFaceConverter implements AttributeConverter<PathFaceOptions, PGobject> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PGobject convertToDatabaseColumn(PathFaceOptions val) {
        try {
            PGobject po = new PGobject();
            po.setType("jsonb");
            po.setValue(mapper.writeValueAsString(val));
            return po;
        } catch (SQLException e) {
            Lg.exception(e);
            return null;
        } catch (JsonGenerationException e) {
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
    public PathFaceOptions convertToEntityAttribute(PGobject po) {
        try {
            //mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            return mapper.readValue(po.getValue(), PathFaceOptions.class);
        } catch (Exception e) {
            Lg.error(e.toString());
            return new PathFaceOptions();
        }
    }
}