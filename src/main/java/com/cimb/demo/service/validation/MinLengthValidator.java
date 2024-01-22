package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_MIN_LENGTH_VALIDATOR;

@Component(FORM_MIN_LENGTH_VALIDATOR)
public class MinLengthValidator implements Validator{
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        if (!validData(payload)) return null;

        final var key = payload.getKey();
        if (payload.getData().get(key).getAsString().length() < payload.getMinLength()) {
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
