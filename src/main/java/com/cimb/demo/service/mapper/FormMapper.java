package com.cimb.demo.service.mapper;

import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.entity.enums.FormType;
import com.cimb.demo.repository.FormRepository;
import com.cimb.demo.service.dto.FormDTO;
import com.cimb.demo.service.mapper.custom.Custom;
import com.cimb.demo.service.mapper.custom.CustomMapper;
import com.cimb.demo.service.mapper.custom.DefaultIgnoreMapping;
import com.cimb.demo.service.mapper.custom.JsonToListMapper;
import com.cimb.demo.service.mapper.custom.ListToStringMapper;
import com.cimb.demo.service.mapper.custom.ObjectToJsonMapper;
import com.cimb.demo.service.mapper.custom.StringToListMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        config = DefaultConfigMapper.class,
        imports = {FormType.class},
        uses = {CustomMapper.class}
)
public interface FormMapper extends EntityMapper<FormDTO, FormEntity> {

    @Override
    @Named("toEntity")
    @Mapping(target = "tags", source = "tags", qualifiedBy = {Custom.class, ListToStringMapper.class})
    @Mapping(target = "components", source = "components", qualifiedBy = {Custom.class, ObjectToJsonMapper.class})
    @Mapping(target = "submissionAccess", source = "submissionAccess", qualifiedBy = {Custom.class, ObjectToJsonMapper.class})
    @Mapping(target = "type", expression = "java(FormType.getFormType(dto.getType()))")
    @DefaultIgnoreMapping
    FormEntity toEntity(FormDTO dto);

    @Override
    @Named("toDto")
    @Mapping(target = "tags", source = "tags", qualifiedBy = {Custom.class, StringToListMapper.class})
    @Mapping(target = "components", source = "components", qualifiedBy = {Custom.class, JsonToListMapper.class})
    @Mapping(target = "type", expression = "java(entity.getType().getName())")
    @Mapping(target = "submissionAccess", source = "submissionAccess", qualifiedBy = {Custom.class, JsonToListMapper.class})
    FormDTO toDto(FormEntity entity);
}
