package org.idiot.yesslave.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toDto(String request, Class<T> type) {
        try {
            return objectMapper.readValue(request, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
