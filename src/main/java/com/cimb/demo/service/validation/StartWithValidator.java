package com.cimb.demo.service.validation;

import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.google.gson.JsonElement;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_START_WITH_VALIDATOR;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Component(FORM_START_WITH_VALIDATOR)
public class StartWithValidator implements Validator {
    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        final var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        if (ObjectUtils.isEmpty(data)) return null;

        final var customMsg = payload.getCustomMsg();
        String msg = customMsg;
        if (data.startsWith(payload.getPrefix())) return null;

        if (ObjectUtils.isEmpty(customMsg)) {
            msg = "Field {%s} must be require!".formatted(payload.getKey());
        }
        return new ValidationResult(msg, false);
    }
}
