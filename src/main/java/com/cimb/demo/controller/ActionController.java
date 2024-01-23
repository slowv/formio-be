package com.cimb.demo.controller;

import com.cimb.demo.service.dto.ActionDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/actions")
public interface ActionController {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<ActionDTO> createAction(@RequestBody @Valid ActionDTO actionDTO);
}
