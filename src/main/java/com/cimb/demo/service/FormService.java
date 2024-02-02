package com.cimb.demo.service;

import com.cimb.demo.entity.enums.FormType;
import com.cimb.demo.service.dto.FormDTO;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;

import java.util.List;

public interface FormService {

    FormDTO save(final FormDTO formDTO);

    FormDTO update(String formId, final FormDTO formDTO);

    FormDTO get(final String formId);

    PagingResponse<FormDTO> getFormByType(FormType type, PagingRequest request);

    List<FormDTO> all();
}
