package com.bijinsuo.common.utils.gson.converter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>,JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date_s = json.getAsString();

        Instant instant = Instant.parse(date_s);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, TimeZone.getTimeZone("Asia/Shangha").toZoneId());
        return localDateTime;
    }

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(TimeZone.getTimeZone("Asia/Shangha").toZoneId());
        ZonedDateTime convert = zonedDateTime.withZoneSameInstant(ZoneOffset.ofHours(8));
        return new JsonPrimitive(convert.toLocalDateTime().toString()+"Z");
    }
}
