package com.cimb.demo.controller.impl;

import com.cimb.demo.controller.FormController;
import com.cimb.demo.service.FormService;
import com.cimb.demo.service.dto.FormDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class FormControllerImpl implements FormController {

    private final FormService formService;

    @Value("${cimb.client-app.name}")
    private String applicationName;

    @Override
    public ResponseEntity<FormDTO> createForm(final FormDTO formDTO) {
        log.debug("REST request to save Form : {}", formDTO);
        final var result = formService.save(formDTO);
        return ResponseEntity
                .created(URI.create("/api/forms/%s".formatted(result.getId())))
                .body(result);
    }

    @Override
    public ResponseEntity<FormDTO> getForm(final String formId) {
        log.debug("REST request to get a Form : {}", formId);
        return ResponseEntity.ok(formService.get(formId));
    }

    @Override
    public ResponseEntity<FormDTO> update(final String formId, final FormDTO formDTO) {
        return ResponseEntity.ok(formService.update(formId, formDTO));
    }
}
