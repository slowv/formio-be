package com.cimb.demo.service.validation.dto;

import com.jayway.jsonpath.Filter;
import lombok.Data;

import java.util.function.BiConsumer;

@Data
public class ValidationSetting<T, R> {
    private String jsonPath;
    private Class<?> type;
    private BiConsumer<T, R> setter;
    private Filter filter;
}
