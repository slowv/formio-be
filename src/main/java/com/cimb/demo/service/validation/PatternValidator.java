package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;

import static com.cimb.demo.common.constants.BeanConstants.FORM_PATTERN_VALIDATOR;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Component(FORM_PATTERN_VALIDATOR)
public class PatternValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        if (ObjectUtils.isEmpty(data)) return null;

        if (Pattern.matches(payload.getPattern(), data)) {
            return null;
        }
        return new ValidationResult(
                ObjectUtils.isEmpty(payload.getCustomMsg())
                        ? "Field %s Pattern is not match!".formatted(payload.getKey())
                        : payload.getCustomMsg(),
                false);
    }
}
