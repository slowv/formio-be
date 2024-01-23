package com.cimb.demo.service.validation.dto;

import com.cimb.demo.service.validation.Validator;
import com.google.gson.JsonObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class PayloadDTO {
    private String key;
    private String type;
    private String pattern;
    private boolean required;
    private int maxLength;
    private int minLength;
    private String prefix;
    private String suffix;
    private String formId;
    private Object data;
    private String customMsg;
    private int min;
    private int max;
    private List<Validator> validators = new ArrayList<>();

    public List<ValidationResult> validate() {
        return this.validators.stream()
                .map(validator -> validator.execute(this))
                .filter(Objects::nonNull)
                .toList();
    }
    public void addValidator(Validator validator) {
        this.validators.add(validator);
    }
}
