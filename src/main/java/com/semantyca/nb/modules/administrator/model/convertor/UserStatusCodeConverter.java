package com.semantyca.nb.modules.administrator.model.convertor;


import com.semantyca.nb.core.user.constants.UserStatusCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusCodeConverter implements AttributeConverter<UserStatusCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserStatusCode type) {
        return type.getCode();
    }

    @Override
    public UserStatusCode convertToEntityAttribute(Integer val) {
        return UserStatusCode.getType(val);
    }
}
