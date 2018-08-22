package com.semantyca.nb.core.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    public static final String DATE_TIME_JSON_FORMAT = "dd.MM.yyyy kk:mm";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_JSON_FORMAT);

    @Override
    public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
        String formattedDate = dateTimeFormatter.format(date);
        generator.writeString(formattedDate);
    }


}
