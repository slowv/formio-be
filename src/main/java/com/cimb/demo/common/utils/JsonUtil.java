package com.cimb.demo.common.utils;

import com.jayway.jsonpath.DocumentContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {
    public static <T> T getJsonValue(DocumentContext context, String jsonPath, Class<T> type) {
        try {
            return context.read(jsonPath, type);
        } catch (Exception ex) {
            return null;
        }
    }
}
