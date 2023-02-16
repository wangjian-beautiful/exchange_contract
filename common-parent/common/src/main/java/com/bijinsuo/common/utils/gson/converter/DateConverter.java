package com.bijinsuo.common.utils.gson.converter;

import com.google.gson.*;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    static final String UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @SneakyThrows
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date_s = json.getAsString();
        SimpleDateFormat sdf = new SimpleDateFormat(UTC);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shangha"));
        Date parse = sdf.parse(date_s);
        return parse;
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        SimpleDateFormat sdf = new SimpleDateFormat(UTC);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shangha"));
        String format = sdf.format(src);
        return new JsonPrimitive(format);
    }
}
