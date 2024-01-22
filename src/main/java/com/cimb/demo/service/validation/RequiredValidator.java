package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_REQUIRED_VALIDATOR;

@Slf4j
@Component(FORM_REQUIRED_VALIDATOR)
public class RequiredValidator implements Validator {

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = payload.getData();
        final var key = payload.getKey();
        if (!validData(payload)) return null;

        boolean result = true;
        String message = payload.getCustomMsg();
        if (ObjectUtils.isEmpty(data.get(key).getAsString())) {
            if (ObjectUtils.isEmpty(message)) {
                message = "Field {%s} must be require!".formatted(key);
            }
            result = false;
        }
        return result ? null : new ValidationResult(message, result);
    }
}
