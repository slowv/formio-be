package com.cimb.demo.service.mapper;

import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.repository.FormRepository;
import com.cimb.demo.service.dto.SubmissionDTO;
import com.cimb.demo.service.mapper.custom.Custom;
import com.cimb.demo.service.mapper.custom.CustomMapper;
import com.cimb.demo.service.mapper.custom.DefaultIgnoreMapping;
import com.cimb.demo.service.mapper.custom.FetchFormEntityMapper;
import com.cimb.demo.service.mapper.custom.JsonToObjectMapper;
import com.cimb.demo.service.mapper.custom.ObjectToJsonMapper;
import com.cimb.demo.service.mapper.custom.StringToListMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = DefaultConfigMapper.class, uses = {CustomMapper.class})
public interface SubmissionMapper extends EntityMapper<SubmissionDTO, SubmissionEntity> {

    @Override
    @Named("toEntity")
    @Mapping(target = "form", source = "formId", qualifiedBy = {Custom.class, FetchFormEntityMapper.class})
    @Mapping(target = "data", source = "data", qualifiedBy = {Custom.class, ObjectToJsonMapper.class})
    @Mapping(target = "metadata", source = "metadata", qualifiedBy = {Custom.class, ObjectToJsonMapper.class})
    @Mapping(target = "access", ignore = true)
//    @Mapping(target = "role", ignore = true)
    @DefaultIgnoreMapping
    SubmissionEntity toEntity(SubmissionDTO dto);

    @Override
    @Named("toDto")
    @Mapping(target = "formId", expression = "java(entity.getForm().getId())")
    @Mapping(target = "data", source = "data", qualifiedBy = {Custom.class, JsonToObjectMapper.class})
    @Mapping(target = "metadata", source = "metadata", qualifiedBy = {Custom.class, JsonToObjectMapper.class})
    @Mapping(target = "access", ignore = true)
//    @Mapping(target = "role", ignore = true)
    SubmissionDTO toDto(SubmissionEntity entity);
}
