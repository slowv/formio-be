package com.cimb.demo.service.impl;

import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.entity.enums.FormType;
import com.cimb.demo.repository.FormRepository;
import com.cimb.demo.service.FormService;
import com.cimb.demo.service.dto.FormDTO;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;
import com.cimb.demo.service.mapper.FormMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public FormDTO update(String formId, final FormDTO formDTO) {
        final var formExist = formRepository.getReferenceById(formId);
        final var formNew = formMapper.toEntity(formDTO);
        formNew.setCode(formExist.getCode());
        formNew.setCreatedDate(formExist.getCreatedDate());
        return formMapper.toDto(formRepository.save(formNew));
    }

    @Override
    public FormDTO get(final String formId) {
        log.debug("Request to get FormId : {}", formId);
        return formMapper.toDto(formRepository.getReferenceById(formId));
    }

    @Override
    public PagingResponse<FormDTO> getFormByType(FormType type, PagingRequest request) {
        log.debug("Request to get all Form by type : {}", type);
        Page<FormEntity> forms = formRepository.findAllByType(type, request.pageable());
        List<FormDTO> responses = forms.stream().map(formMapper::toDto).toList();
        return new PagingResponse<>(responses,
                forms.getTotalElements(),
                forms.getTotalPages(),
                request);
    }

    @Override
    public List<FormDTO> all() {
        return formMapper.toDto(formRepository.findAll());
    }
}
