package com.cimb.demo.controller;

import com.cimb.demo.service.dto.FormDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
