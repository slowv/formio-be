package com.cimb.demo.controller;

import com.cimb.demo.service.dto.SubmissionDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "/api/submissions")
public interface SubmissionController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ResponseEntity<SubmissionDTO> createSubmission(
            @RequestBody @Valid SubmissionDTO submissionDTO
    );
}
