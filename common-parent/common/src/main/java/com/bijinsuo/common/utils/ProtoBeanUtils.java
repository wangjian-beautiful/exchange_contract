package com.bijinsuo.common.utils;

import com.bijinsuo.common.utils.gson.converter.BigDecimalConverter;
import com.bijinsuo.common.utils.gson.converter.DateConverter;
import com.bijinsuo.common.utils.gson.converter.LocalDateTimeConverter;
import com.bijinsuo.common.utils.gson.converter.TimeStampConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.Message;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProtoBeanUtils {

    @SneakyThrows
    public static <T> T toPojoBean(Class<T> destPojoClass, Message sourceMessage) {
        if (destPojoClass == null) {
            throw new IllegalArgumentException
                    ("No destination pojo class specified");
        }
        if (sourceMessage == null) {
            throw new IllegalArgumentException("No source message specified");
        }
        if (StringUtils.isEmpty(sourceMessage.toString())) {
            return null;
        }
        String json = JsonFormat.printer().print(sourceMessage);
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        return gson.fromJson(json, destPojoClass);
    }

    /**
     * Message.Builder = XXXPO.newBuilder()
     * @param destBuilder
     * @param sourcePojoBean
     * @throws IOException
     */
    @SneakyThrows
    public static void toProtoBean(Message.Builder destBuilder, Object sourcePojoBean) {
        if (destBuilder == null) {
            throw new IllegalArgumentException
                    ("No destination message builder specified");
        }
        if (sourcePojoBean == null) {
            return;
        }
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateConverter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
                .registerTypeAdapter(Timestamp.class, new TimeStampConverter())
                .registerTypeAdapter(BigDecimal.class, new BigDecimalConverter());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String json = gson.toJson(sourcePojoBean);
//        System.out.println(json);
        JsonFormat.parser().ignoringUnknownFields().merge(json, destBuilder);
    }

    public static <T extends Message> List<T> toProtoBeanList(T message, List sourceList) {
        Assert.notNull(sourceList, "sourceList must be not null");
        List<T> result = new ArrayList<>();
        for (Object o : sourceList) {
            Message.Builder builder = message.toBuilder();
            toProtoBean(builder, o);
            result.add((T) builder.build());
        }
        return result;
    }

    public static <T> List<T> toPojoBeanList(Class<T> destPojoClass, List<? extends Message> sourceMessageList) {
        Assert.notNull(sourceMessageList, "sourceMessageList must be not null");
        List<T> result = new ArrayList<>();
        for (Message message : sourceMessageList) {
            result.add(toPojoBean(destPojoClass, message));
        }
        return result;
    }

    /**
     * Message = AccountPO.getDefaultInstance(),
     * return AccountPO.
     * @param message
     * @param sourcePojoBean
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T extends Message> T toProtoBean (T message, Object sourcePojoBean) {
        Message.Builder builder = message.toBuilder();
        if (sourcePojoBean != null) {
            toProtoBean(builder, sourcePojoBean);
        }
        return (T) builder.build();
    }
}
