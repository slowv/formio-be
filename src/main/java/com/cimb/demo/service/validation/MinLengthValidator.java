package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_MIN_LENGTH_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MIN_LENGTH;
import static com.cimb.demo.common.constants.BeanConstants.KEY_VALIDATE;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Component(FORM_MIN_LENGTH_VALIDATOR)
public class MinLengthValidator extends Validator {
    public MinLengthValidator() {
        super("$.%s.%s".formatted(KEY_VALIDATE, KEY_MIN_LENGTH));
    }

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        if (ObjectUtils.isEmpty(data)) return null;

        final var key = payload.getKey();
        if (data.length() < payload.getMinLength()) {
            return new ValidationResult(
                    ObjectUtils.isEmpty(payload.getCustomMsg())
                            ? "Field %s must be length large than %s".formatted(key, payload.getMinLength())
                            : payload.getCustomMsg(),
                    false
            );
        }

        return null;
    }
}
