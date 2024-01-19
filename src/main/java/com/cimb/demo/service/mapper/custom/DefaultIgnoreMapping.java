package com.cimb.demo.service.mapper.custom;

import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Mapping(target = "id", ignore = true)
@Mapping(target = "version", ignore = true)
@Mapping(target = "createdDate", ignore = true)
@Mapping(target = "lastModifiedDate", ignore = true)
@Mapping(target = "lastModifiedBy", ignore = true)
@Mapping(target = "deletedDate", ignore = true)
@Mapping(target = "createdBy", ignore = true)
public @interface DefaultIgnoreMapping {
}
