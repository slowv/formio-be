package com.cimb.demo.service.validation;

import com.cimb.demo.controller.SubmissionController;
import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.cimb.demo.common.constants.BeanConstants.FORM_UNIQUE_VALIDATOR;

@Slf4j
@Component(FORM_UNIQUE_VALIDATOR)
@RequiredArgsConstructor
public class UniqueValidator {
    private final SubmissionRepository submissionRepository;

    /**
     * @param key is key json data
     * @param value is value json data
     * @param code is code of record submission, read more info here {@link SubmissionEntity}
     * */
    public ValidationResult execute(final String key, final String value, final String code) {
        return new ValidationResult("", true);
    }
}
