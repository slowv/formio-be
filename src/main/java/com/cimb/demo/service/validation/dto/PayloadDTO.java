package com.cimb.demo.service.validation.dto;

import com.cimb.demo.service.validation.Validator;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

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
    private String data;
    private String customMsg;
    private int min;
    private int max;
    private List<Validator> validators = new ArrayList<>();

    public PayloadDTO(String formId, String data) {
        this.formId = formId;
        this.data = data;
    }

    public List<ValidationResult> validate() {
        return this.validators.stream()
                .map(validator -> validator.execute(this))
                .filter(Objects::nonNull)
                .toList();
    }

    public void addValidator(Validator validator) {
        if (ObjectUtils.isEmpty(validator)) return;
        this.validators.add(validator);
    }

    public PayloadDTO addValidator(PayloadMapValue<?> map) {
        this.addValidator(map.setValue(this));
        return this;
    }

    public PayloadDTO addValidator(List<PayloadMapValue<?>> maps) {
        maps.forEach(this::addValidator);
        return this;
    }

    public PayloadDTO allValidator(ApplicationContext applicationContext, Object component) {
        this.addValidator(PayloadMapValue.all(applicationContext, component));
        return this;
    }
}
