package com.santy.httpserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static ObjectMapper serverObjectMapper =defaultServerObjectMapper();
    private static ObjectMapper defaultServerObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static JsonNode parse(String jsonSource) throws IOException {
      return serverObjectMapper.readTree(jsonSource);
    };

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException  {
        return serverObjectMapper.treeToValue(node, aClass);
    }

    public static JsonNode toJson(Object obj) throws JsonProcessingException {
        return serverObjectMapper.valueToTree(obj);
    }

    private static String generateJson(Object obj,boolean pretty) throws JsonProcessingException {
        ObjectWriter writer = serverObjectMapper.writer();
        if(pretty) {
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        }
        return writer.writeValueAsString(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
    return generateJson(node,false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node,true);
    }
}
