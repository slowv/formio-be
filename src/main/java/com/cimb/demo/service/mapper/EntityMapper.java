package com.cimb.demo.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
public interface EntityMapper<D, E> {
    @Named("toEntity")
    E toEntity(D dto);

    @Named("toDto")
    D toDto(E entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<E> toEntity(List<D> dtoList);

    @IterableMapping(qualifiedByName = "toDto")
    List<D> toDto(List<E> entityList);
}
