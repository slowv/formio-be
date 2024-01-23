package com.cimb.demo.service.validation;

import com.cimb.demo.repository.SubmissionRepository;
import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_UNIQUE_VALIDATOR;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;

@Slf4j
@Component(FORM_UNIQUE_VALIDATOR)
@RequiredArgsConstructor
public class UniqueValidator implements Validator {
    private final SubmissionRepository submissionRepository;

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        var data = getJsonValue(JsonPath.parse(payload.getData()), "@.%s".formatted(payload.getKey()), String.class);
        if (ObjectUtils.isEmpty(data)) {
            data = "";
        }

        final var key = payload.getKey();
        final var results = submissionRepository.uniqueValue(key, data, payload.getFormId());
        return ObjectUtils.isEmpty(results) ? null : new ValidationResult("Field %s is must unique!".formatted(key), false);
    }
}
