package com.semantyca.officeframe.modules.reference.model.constants.converter;

import com.semantyca.officeframe.modules.reference.model.constants.CountryCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CountryCodeConverter implements AttributeConverter<CountryCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CountryCode lt) {
        return lt.getCode();
    }

    @Override
    public CountryCode convertToEntityAttribute(Integer lt) {
        return CountryCode.getType(lt);
    }
}
