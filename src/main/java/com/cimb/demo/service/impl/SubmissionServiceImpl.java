package com.cimb.demo.service.impl;

import com.cimb.demo.component.ValidatorDataExtractor;
import com.cimb.demo.controller.errors.FormValidationException;
import com.cimb.demo.repository.SubmissionRepository;
import com.cimb.demo.service.SubmissionService;
import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.mapper.SubmissionMapper;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final ValidatorDataExtractor validatorDataExtractor;

    @Override
    public SubmissionDTO save(final SubmissionDTO submissionDTO) {
        log.debug("Request to save Submission : {}", submissionDTO);
        final var entity = submissionMapper.toEntity(submissionDTO);

        final List<String> obj = JsonPath.read(
                entity.getForm().getComponents(),
                "@[*].values[?]",
                filter(where("label").is("Nam"))
        );
//        System.out.println(obj);

        final var extract = validatorDataExtractor.extract(entity);
        if (ObjectUtils.isEmpty(extract)) {
            return submissionMapper.toDto(submissionRepository.save(entity));
        }

        throw new FormValidationException(HttpStatus.BAD_REQUEST.getReasonPhrase(), "validation", extract);
    }
}
