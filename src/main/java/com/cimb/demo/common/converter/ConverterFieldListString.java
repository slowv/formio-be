package com.cimb.demo.common.converter;

import com.amazonaws.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter(autoApply = true)
public class ConverterFieldListString implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return null == list ? "" : String.join(",", list);
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        return StringUtils.isNullOrEmpty(joined) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(joined.split(",")));
    }
}
