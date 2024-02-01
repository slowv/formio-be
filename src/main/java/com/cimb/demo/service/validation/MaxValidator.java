package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;

import static com.cimb.demo.common.constants.BeanConstants.FORM_MAX_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MAX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_VALIDATE;

@Component(FORM_MAX_VALIDATOR)
public class MaxValidator extends Validator {
    public MaxValidator() {
        super( "$.%s.%s".formatted(KEY_VALIDATE, KEY_MAX));
    }

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        return null;
    }
}
