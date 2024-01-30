package com.cimb.demo.component;

import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.service.validation.dto.PairResultValidation;
import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jayway.jsonpath.JsonPath.parse;

@Component
@RequiredArgsConstructor
public class ValidatorDataExtractor {

    private final ApplicationContext applicationContext;

    /**
     * @param submission is data submitted, read more info here {@link SubmissionEntity}
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<ValidationResult>> extract(final SubmissionEntity submission) {
        final List<Object> components = (List<Object>) parse(submission.getForm().getComponents())
                .read("$[*]", List.class);

        return components
                .stream()
                .map(component ->
                        new PayloadDTO(submission.getForm().getId(), submission.getData())
                                .allValidator(applicationContext, component)
                )
                .map(payload -> new PairResultValidation(
                        payload.getKey(),
                        payload.validate()))
                .filter(pairResult -> !CollectionUtils.isEmpty(pairResult.validationResults()))
                .collect(Collectors.toMap(PairResultValidation::key, PairResultValidation::validationResults));
    }
}
