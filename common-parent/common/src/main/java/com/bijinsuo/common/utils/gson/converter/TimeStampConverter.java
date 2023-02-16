package com.bijinsuo.common.utils.gson.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.protobuf.Timestamp;

import java.lang.reflect.Type;
import java.time.Instant;

public class TimeStampConverter implements JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String time_s = json.getAsString();
        Instant instant = Instant.parse(time_s);
        Timestamp result = Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
        return result;
    }
}
