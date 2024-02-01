package com.cimb.demo.controller;

import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/submissions")
public interface SubmissionController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ResponseEntity<SubmissionDTO> createSubmission(
            @RequestBody @Valid SubmissionDTO submissionDTO
    );

    @PostMapping("/{formId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagingResponse<SubmissionDTO>> getSubmissionByForm(@PathVariable("formId") String formId, @RequestBody PagingRequest request);
}
