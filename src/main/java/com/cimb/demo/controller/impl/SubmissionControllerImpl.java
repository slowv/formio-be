package com.cimb.demo.controller.impl;

import com.cimb.demo.controller.SubmissionController;
import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.SubmissionService;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class SubmissionControllerImpl implements SubmissionController {

    private final SubmissionService submissionService;

    @Override
    public ResponseEntity<SubmissionDTO> createSubmission(final SubmissionDTO submissionDTO) {
        log.debug("REST request to save Submission : {}", submissionDTO);
        final var result = submissionService.save(submissionDTO);
        return ResponseEntity
                .created(URI.create("/api/submissions/%s".formatted(result.getId())))
                .body(result);
    }

    @Override
    public ResponseEntity<PagingResponse<SubmissionDTO>> getSubmissionByForm(String formId, PagingRequest request) {
        log.debug("REST request to get submission by form: {}", formId);
        final var result = submissionService.getSubmissionByForm(formId, request);
        return ResponseEntity
                .created(URI.create("/api/submissions/%s".formatted(formId)))
                .body(result);
    }
}
