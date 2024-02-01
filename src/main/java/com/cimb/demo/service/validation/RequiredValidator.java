package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_REQUIRED_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY_REQUIRED;
import static com.cimb.demo.common.constants.BeanConstants.KEY_VALIDATE;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Slf4j
@Component(FORM_REQUIRED_VALIDATOR)
public class RequiredValidator extends Validator {

    public RequiredValidator() {
        super("$.%s.%s".formatted(KEY_VALIDATE, KEY_REQUIRED));
    }

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        boolean result = true;
        String message = payload.getCustomMsg();
        if (ObjectUtils.isEmpty(data)) {
            if (ObjectUtils.isEmpty(message)) {
                message = "Field {%s} must be require!".formatted(payload.getKey());
            }
            result = false;
        }
        return result ? null : new ValidationResult(message, result);
    }
}
