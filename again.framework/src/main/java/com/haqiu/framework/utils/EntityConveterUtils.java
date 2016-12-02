package com.haqiu.framework.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zctang on 2014/9/23 0023.
 */
public class EntityConveterUtils {
    private static ObjectMapper mapper;

    /**
     * 获取ObjectMapper实例
     * @param createNew 方式：true，新实例；false,存在的mapper实例
     * @return
     */
    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew || mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static <T> T conveterJsonToEntity(String jsonString,Class<T> c){
        T t;
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            t = objectMapper.readValue(jsonString, c);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String conveterEntityToJson(Object entity){
        String json;
        try {
            ObjectMapper objectMapper = getMapperInstance(false);
            json = objectMapper.writeValueAsString(entity);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class CustomDateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            String formattedDate = formatter.format(value);
            jgen.writeString(formattedDate);
        }
    }
}
