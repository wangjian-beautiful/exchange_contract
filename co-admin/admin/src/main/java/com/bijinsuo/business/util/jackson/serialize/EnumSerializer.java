package com.bijinsuo.business.util.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Winter
 */
public class EnumSerializer<T> extends JsonSerializer<T> {
  @Override
  public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    Class<?> clazz = t.getClass();
    if (clazz.isEnum()) {
      try {
        Field desc = clazz.getField("desc");
        jsonGenerator.writeString(desc.get(t).toString());
      } catch (NoSuchFieldException | IllegalAccessException e) {
        jsonGenerator.writeString(((Enum<?>) t).name());
      }
    }else {
      jsonGenerator.writeString("");
    }
  }
}
