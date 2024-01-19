package com.cimb.demo.service.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_REQUIRED_VALIDATOR;

@Slf4j
@Component(FORM_REQUIRED_VALIDATOR)
public class RequiredValidator {

    public ValidationResult execute(final String field, final String data, final String customMessage) {
        boolean result = true;
        String message = customMessage;
        if (ObjectUtils.isEmpty(data)) {
            if (ObjectUtils.isEmpty(customMessage)) {
                message = "Field {%s} must be require!".formatted(field);
            }
            result = false;
        }
        return result ? null : new ValidationResult(message, result);
    }
}
