package com.cimb.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum FormType {
    FORM("form"), RESOURCE("resource");

    private final String name;

    public static FormType getFormType(String name) {
        return Stream.of(FormType.values())
                .filter(type -> Objects.equals(type.getName(), name))
                .findFirst()
                .orElseThrow();
    }
}
