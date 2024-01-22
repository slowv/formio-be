package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_START_WITH_VALIDATOR;

@Component(FORM_START_WITH_VALIDATOR)
public class StartWithValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = payload.getData();
        final var key = payload.getKey();
       if (!validData(payload)) return null;

        boolean result = true;
        final var customMsg = payload.getCustomMsg();
        String msg = customMsg;

        final var jsonElement = data.get(key);
        if (!ObjectUtils.isEmpty(jsonElement) && !jsonElement.getAsString().startsWith(payload.getPrefix())) {
            result = false;
            if (ObjectUtils.isEmpty(customMsg)) {
                msg = "Field {%s} must be require!".formatted(key);
            }
        }
        return result ? null : new ValidationResult(msg, result);
    }
}
