package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_END_WITH_VALIDATOR;

@Component(FORM_END_WITH_VALIDATOR)
public class EndWithValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = payload.getData();
        if (!validData(payload)) return null;

        boolean result = true;
        String msg = payload.getCustomMsg();

        final var key = payload.getKey();
        if (!data.get(key).getAsString().endsWith(payload.getSuffix())) {
            result = false;
            if (ObjectUtils.isEmpty(payload.getCustomMsg())) {
                msg = "Field {%s} must be require!".formatted(key);
            }
        }
        return result ? null : new ValidationResult(msg, result);
    }
}
