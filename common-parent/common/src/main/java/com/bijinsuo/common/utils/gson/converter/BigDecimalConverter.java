package com.bijinsuo.common.utils.gson.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalConverter implements JsonSerializer<BigDecimal> {
    @Override
    public JsonElement serialize(BigDecimal src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toPlainString());
    }
}
