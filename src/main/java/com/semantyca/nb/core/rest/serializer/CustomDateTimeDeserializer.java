package com.semantyca.nb.core.rest.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(CustomDateTimeSerializer.DATE_TIME_JSON_FORMAT);

    @Override
    public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
        return LocalDateTime.parse(jsonparser.getText(), dateTimeFormatter);
    }
}
