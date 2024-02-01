package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_MAX_LENGTH_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MAX_LENGTH;
import static com.cimb.demo.common.constants.BeanConstants.KEY_VALIDATE;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Component(FORM_MAX_LENGTH_VALIDATOR)
public class MaxLengthValidator extends Validator {
    public MaxLengthValidator() {
        super("$.%s.%s".formatted(KEY_VALIDATE, KEY_MAX_LENGTH));
    }

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        if (ObjectUtils.isEmpty(data)) return null;

        final var key = payload.getKey();
        if (data.length() > payload.getMaxLength()) {
            return new ValidationResult(
                    ObjectUtils.isEmpty(payload.getCustomMsg())
                    ? "Field %s must be length less than %s".formatted(key, payload.getMaxLength())
                    : payload.getCustomMsg(),
                    false
            );
        }

        return null;
    }
}
