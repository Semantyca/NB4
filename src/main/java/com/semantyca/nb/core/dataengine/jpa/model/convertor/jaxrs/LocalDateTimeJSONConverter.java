package com.semantyca.nb.core.dataengine.jpa.model.convertor.jaxrs;
/*
import org.apache.johnzon.mapper.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeJSONConverter implements Converter<LocalDateTime> {
    public static final String DATE_TIME_JSON_FORMAT = "dd.MM.yyyy kk:mm";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_JSON_FORMAT);

    @Override
    public String toString(final LocalDateTime instance) {
        return dateTimeToStringSilently(instance);
    }

    @Override
    public LocalDateTime fromString(final String text) {
        return stringToDateTime(text);
    }

    private static String dateTimeToStringSilently(LocalDateTime dateTime) {
        try {
            return dateTimeFormatter.format(dateTime);
        } catch (Exception e) {
            return "";
        }
    }

    private static LocalDateTime stringToDateTime(String text) {
        try {
            return LocalDateTime.parse(text, dateTimeFormatter);
        } catch (Exception e) {
            return null;
        }
    }
}*/
