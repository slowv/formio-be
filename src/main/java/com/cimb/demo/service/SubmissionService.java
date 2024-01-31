package com.cimb.demo.service;

import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;

public interface SubmissionService {
    SubmissionDTO save(SubmissionDTO submissionDTO);

    PagingResponse<SubmissionDTO> getSubmissionByForm(String formId, PagingRequest request);
}
