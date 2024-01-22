package com.cimb.demo.service.validation;

import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.repository.SubmissionRepository;
import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.cimb.demo.common.constants.BeanConstants.FORM_UNIQUE_VALIDATOR;

@Slf4j
@Component(FORM_UNIQUE_VALIDATOR)
@RequiredArgsConstructor
public class UniqueValidator implements Validator {
    private final SubmissionRepository submissionRepository;

    @Override
    public ValidationResult execute(final PayloadDTO payload) {
        if (!validData(payload)) return null;

        final var key = payload.getKey();
        final var results = submissionRepository.uniqueValue(key, payload.getData().get(key).getAsString(), payload.getFormId());
        return ObjectUtils.isEmpty(results) ? null : new ValidationResult("Field %s is must unique!".formatted(key), false);
    }
}
