package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class MinValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        if (!validData(payload)) return null;

        return payload.getData().get(payload.getKey()).getAsString().length() > payload.getMin()
                ? null
                : new ValidationResult("Field %s must be large than %s".formatted(payload.getMin(), payload.getKey()), false);
    }
}
