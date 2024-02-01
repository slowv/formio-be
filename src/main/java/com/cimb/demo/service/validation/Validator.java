package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.DocumentContext;
import lombok.Data;

import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Data
public abstract class Validator {
    protected String jsonPath;

    public Validator(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public abstract ValidationResult execute(PayloadDTO payload);

    public <R> R getValue(DocumentContext documentContext, Class<R> type) {
        return getJsonValue(documentContext, this.jsonPath, type);
    }
}
