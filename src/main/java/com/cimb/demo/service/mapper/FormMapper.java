package com.cimb.demo.service.mapper;

import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.entity.enums.FormType;
import com.cimb.demo.service.dto.FormDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;

@Mapper(
        config = DefaultConfigMapper.class,
        imports = {Arrays.class, Gson.class, TypeToken.class, FormType.class}
)
public interface FormMapper extends EntityMapper<FormDTO, FormEntity> {

    @Override
    @Mapping(target = "tags", expression = "java(String.join(\", \", dto.getTags()))")
    @Mapping(target = "components", expression = "java(new Gson().toJson(dto.getComponents()))")
    @Mapping(target = "type", expression = "java(FormType.getFormType(dto.getType()))")
    FormEntity toEntity(FormDTO dto);

    @Override
    @Mapping(target = "tags", expression = "java(Arrays.asList(entity.getTags().split(\", \")))")
    @Mapping(target = "components", expression = "java(new Gson().fromJson(entity.getComponents(), new TypeToken<ArrayList<Object>>(){}.getType()))")
    @Mapping(target = "type", expression = "java(entity.getType().getName())")
    FormDTO toDto(FormEntity entity);
}
