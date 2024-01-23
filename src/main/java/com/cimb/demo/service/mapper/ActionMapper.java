package com.cimb.demo.service.mapper;

import com.cimb.demo.entity.ActionEntity;
import com.cimb.demo.service.dto.ActionDTO;
import com.cimb.demo.service.mapper.custom.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = DefaultConfigMapper.class, uses = {CustomMapper.class})
public interface ActionMapper extends EntityMapper<ActionDTO, ActionEntity> {

    @Override
    @Named("toEntity")
    @Mapping(target = "form", source = "formId", qualifiedBy = {Custom.class, FetchFormEntityMapper.class})
    @Mapping(target = "settings", source = "settings", qualifiedBy = {Custom.class, ObjectToJsonMapper.class})
    @Mapping(target = "condition", source = "condition", qualifiedBy = {Custom.class, ObjectToJsonMapper.class})
    @DefaultIgnoreMapping
    ActionEntity toEntity(ActionDTO dto);

    @Override
    @Named("toDto")
    ActionDTO toDto(ActionEntity entity);
}
