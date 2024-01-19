package com.cimb.demo.service.mapper.custom;


import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.repository.FormRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Custom
@Component
public class CustomMapper {
    private final Gson gson = new Gson();
    private final FormRepository formRepository;

    public CustomMapper(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @ObjectToJsonMapper
    public String convertObjectToJson(Object obj) {
        return gson.toJson(obj);
    }

    @ListToStringMapper
    public String convertListToString(List<String> sources) {
        return String.join(", ", sources);
    }

    @StringToListMapper
    public List<String> convertStringToList(String source) {
        return Arrays.asList(source.split(", "));
    }

    @JsonToListMapper
    public List<Object> convertJsonToList(String source) {
        return gson.fromJson(source, new TypeToken<ArrayList<Object>>() {
        }.getType());
    }

    @JsonToObjectMapper
    public Object convertJsonToObject(String source) {
        return gson.fromJson(source, Object.class);
    }

    @FetchFormEntityMapper
    public FormEntity fetchFormEntity(String formId) {
        return formRepository
                .findById(formId)
                .orElseThrow();
    }
}
