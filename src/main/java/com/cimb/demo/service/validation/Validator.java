package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;

public interface Validator {
    ValidationResult execute(PayloadDTO payload);

    default boolean validData(PayloadDTO payload) {
        final var data = payload.getData();
        return data.has(payload.getKey());
    }

}
