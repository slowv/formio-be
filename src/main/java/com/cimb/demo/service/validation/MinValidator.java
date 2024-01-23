package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_MIN_VALIDATOR;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Component(FORM_MIN_VALIDATOR)
public class MinValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        if (ObjectUtils.isEmpty(data)) return null;

        return data.length() > payload.getMin()
                ? null
                : new ValidationResult("Field %s must be large than %s".formatted(payload.getMin(), payload.getKey()), false);
    }
}
