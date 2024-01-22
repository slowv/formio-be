package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_MAX_LENGTH_VALIDATOR;

@Component(FORM_MAX_LENGTH_VALIDATOR)
public class MaxLengthValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = payload.getData();
        if (!validData(payload)) return null;

        final var key = payload.getKey();
        if (!(data.get(key).getAsString().length() < payload.getMaxLength())) {
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
