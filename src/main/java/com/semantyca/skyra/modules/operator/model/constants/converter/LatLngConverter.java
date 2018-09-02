package com.semantyca.skyra.modules.operator.model.constants.converter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.semantyca.nb.logger.Lg;
import com.semantyca.skyra.modules.operator.model.embedded.PointCoordiantes;
import org.postgresql.util.PGobject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Converter
public class LatLngConverter implements AttributeConverter<List<PointCoordiantes>, PGobject> {
    private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    @Override
    public PGobject convertToDatabaseColumn(List<PointCoordiantes> val) {
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
    public List<PointCoordiantes> convertToEntityAttribute(PGobject po) {
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            CollectionType listType = typeFactory.constructCollectionType(List.class, PointCoordiantes.class);
            return mapper.readValue(po.getValue(), listType);
        } catch (Exception e) {
            Lg.error(e.toString());
            return new ArrayList<>();
        }
    }
}