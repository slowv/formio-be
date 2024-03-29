package com.cimb.demo.service.impl;

import com.cimb.demo.component.ValidatorDataExtractor;
import com.cimb.demo.controller.errors.FormValidationException;
import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.repository.SubmissionRepository;
import com.cimb.demo.service.SubmissionService;
import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;
import com.cimb.demo.service.mapper.SubmissionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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
        final var extract = validatorDataExtractor.extract(entity);

        if (ObjectUtils.isEmpty(extract)) {
            return submissionMapper.toDto(submissionRepository.save(entity));
        }

        throw new FormValidationException(HttpStatus.BAD_REQUEST.getReasonPhrase(), "validation", extract);
    }

    @Override
    public PagingResponse<SubmissionDTO> getSubmissionByForm(String formId, PagingRequest request) {
        log.debug("Request to get all submission by formId: {}", formId);
        Page<SubmissionEntity> submissions = submissionRepository.findAllByFormId(formId, request.pageable());
        List<SubmissionDTO> responses = submissions.stream().map(submissionMapper::toDto).toList();
        return new PagingResponse<>(responses,
                submissions.getTotalElements(),
                submissions.getTotalPages(),
                request);
    }
}
