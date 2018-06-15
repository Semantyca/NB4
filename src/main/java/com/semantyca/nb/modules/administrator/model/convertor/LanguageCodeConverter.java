package com.semantyca.nb.modules.administrator.model.convertor;

import com.semantyca.nb.localization.constants.LanguageCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageCodeConverter implements AttributeConverter<LanguageCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LanguageCode type) {
        return type.getCode();
    }

    @Override
    public LanguageCode convertToEntityAttribute(Integer val) {
        return LanguageCode.getType(val);
    }
}
