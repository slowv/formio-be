package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class MaxValidator implements Validator{
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        return null;
    }
}
