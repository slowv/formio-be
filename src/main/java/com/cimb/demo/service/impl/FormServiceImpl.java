package com.cimb.demo.service.impl;

import com.cimb.demo.repository.AccessRepository;
import com.cimb.demo.repository.FormRepository;
import com.cimb.demo.service.FormService;
import com.cimb.demo.service.dto.FormDTO;
import com.cimb.demo.service.mapper.FormMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormMapper formMapper;
    private final FormRepository formRepository;

    @Override
    public FormDTO save(final FormDTO formDTO) {
        log.debug("Request to save Form : {}", formDTO);
        final var formEntity = formMapper.toEntity(formDTO);
        return formMapper.toDto(formRepository.save(formEntity));
    }

    @Override
    public FormDTO get(final String formId) {
        log.debug("Request to get FormId : {}", formId);
        return formMapper.toDto(formRepository.getReferenceById(formId));
    }
}
