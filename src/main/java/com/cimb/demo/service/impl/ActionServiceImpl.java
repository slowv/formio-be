package com.cimb.demo.service.impl;

import com.cimb.demo.repository.ActionRepository;
import com.cimb.demo.service.ActionService;
import com.cimb.demo.service.dto.ActionDTO;
import com.cimb.demo.service.mapper.ActionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionMapper actionMapper;
    private final ActionRepository actionRepository;

    @Override
    public ActionDTO save(ActionDTO actionDTO) {
        final var actionEntity = actionMapper.toEntity(actionDTO);
        return actionMapper.toDto(actionRepository.save(actionEntity));
    }
}
