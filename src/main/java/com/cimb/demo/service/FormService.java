package com.cimb.demo.service;

import com.cimb.demo.service.dto.FormDTO;

public interface FormService {

    FormDTO save(final FormDTO formDTO);

    FormDTO get(final String formId);
}
