package com.semantyca.nb.core.dataengine.jpa.model.convertor.jaxrs;

import org.apache.johnzon.mapper.Converter;

import java.util.UUID;

public class UUIDConverter implements Converter<UUID> {
    @Override
    public String toString(final UUID instance) {
        return instance.toString();
    }

    @Override
    public UUID fromString(final String text) {
        return UUID.fromString(text);
    }
}
