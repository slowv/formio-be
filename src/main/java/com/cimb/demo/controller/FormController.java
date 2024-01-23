package com.cimb.demo.controller;

import com.cimb.demo.entity.enums.FormType;
import com.cimb.demo.service.dto.FormDTO;
import com.cimb.demo.service.dto.request.PagingRequest;
import com.cimb.demo.service.dto.response.PagingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RequestMapping(value = "/api/forms")
public interface FormController {

    /**
     * {@code POST  /} : Create a new form.
     *
     * @param formDTO the formDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formDTO.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<FormDTO> createForm(@RequestBody @Valid FormDTO formDTO);

    @GetMapping("/{formId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FormDTO> getForm(@PathVariable("formId") String formId);

    @PostMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagingResponse<FormDTO>> getFormByType(@PathVariable("type") FormType type, @RequestBody PagingRequest request);

    @PutMapping("/{formId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<FormDTO> update(@PathVariable("formId") String formId, @RequestBody @Valid FormDTO formDTO);
}
