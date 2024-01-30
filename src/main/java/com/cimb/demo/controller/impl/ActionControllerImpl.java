package com.cimb.demo.controller.impl;

import com.cimb.demo.controller.ActionController;
import com.cimb.demo.service.ActionService;
import com.cimb.demo.service.dto.ActionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ActionControllerImpl implements ActionController {
    private final ActionService actionService;

    @Override
    public ResponseEntity<ActionDTO> createAction(ActionDTO actionDTO) {
        log.debug("REST request to save Action : {}", actionDTO);
        final var result = actionService.save(actionDTO);
        return ResponseEntity
                .created(URI.create("/api/actions/%s".formatted(result.getId())))
                .body(result);
    }
}
