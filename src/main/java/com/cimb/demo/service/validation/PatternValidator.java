package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;

import static com.cimb.demo.common.constants.BeanConstants.FORM_PATTERN_VALIDATOR;

@Component(FORM_PATTERN_VALIDATOR)
public class PatternValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        if (!validData(payload)) return null;

        if (Pattern.matches(payload.getPattern(), payload.getData().get(payload.getKey()).getAsString())) {
            return null;
        }
        return new ValidationResult(
                ObjectUtils.isEmpty(payload.getCustomMsg())
                        ? "Field %s Pattern is not match!".formatted(payload.getKey())
                        : payload.getCustomMsg(),
                false);
    }
}
