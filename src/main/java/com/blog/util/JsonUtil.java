package com.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/4.
 */
@Component
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String object2json(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static Object json2object(String json, Class c) throws IOException {
        return objectMapper.readValue(json, c);
    }
}
