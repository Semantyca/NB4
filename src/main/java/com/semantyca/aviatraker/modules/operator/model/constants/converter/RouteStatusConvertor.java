package com.semantyca.aviatraker.modules.operator.model.constants.converter;

import com.semantyca.aviatraker.modules.operator.model.constants.RouteStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RouteStatusConvertor implements AttributeConverter<RouteStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RouteStatus type) {
        return type.getCode();
    }

    @Override
    public RouteStatus convertToEntityAttribute(Integer val) {
        return RouteStatus.getType(val);
    }
}
