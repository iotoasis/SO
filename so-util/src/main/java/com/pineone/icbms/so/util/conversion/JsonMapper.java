package com.pineone.icbms.so.util.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Json-Object Mapper.<BR/>
 *
 * Created by uni4love on 2017. 4. 28..
 */
public class JsonMapper {
    /**
     * object mapper
     */
    private static ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * read Object from json string.<BR/>
     *
     * @param json json string
     * @param clz  value Class type
     * @return Value object that read from json string
     */
    public static <T> T readJsonObject(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * convert object to json string.<BR/>
     *
     * @param obj object that convert to json
     * @return json string
     */
    public static String writeJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
