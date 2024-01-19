package com.cimb.demo.service.impl;

import com.cimb.demo.repository.SubmissionRepository;
import com.cimb.demo.service.SubmissionService;
import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.mapper.SubmissionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;

    @Override
    public SubmissionDTO save(final SubmissionDTO submissionDTO) {
        log.debug("Request to save Submission : {}", submissionDTO);
        final var entity = submissionMapper.toEntity(submissionDTO);
        return submissionMapper.toDto(entity);
    }
}
