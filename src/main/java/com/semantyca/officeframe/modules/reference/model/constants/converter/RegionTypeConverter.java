package com.semantyca.officeframe.modules.reference.model.constants.converter;

import com.semantyca.officeframe.modules.reference.model.constants.RegionCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RegionTypeConverter implements AttributeConverter<RegionCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RegionCode lt) {
        return lt.getCode();
    }

    @Override
    public RegionCode convertToEntityAttribute(Integer lt) {
        return RegionCode.getType(lt);
    }
}
