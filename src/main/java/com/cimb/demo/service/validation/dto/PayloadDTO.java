package com.cimb.demo.service.validation.dto;

import com.cimb.demo.service.validation.Validator;
import com.jayway.jsonpath.DocumentContext;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.cimb.demo.common.constants.BeanConstants.KEY;
import static com.cimb.demo.common.constants.BeanConstants.KEY_CUSTOM_MESSAGE;
import static com.cimb.demo.common.constants.BeanConstants.KEY_PREFIX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_REQUIRED;
import static com.cimb.demo.common.constants.BeanConstants.KEY_SUFFIX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_TYPE;
import static com.cimb.demo.common.constants.BeanConstants.KEY_VALIDATE;
import static com.cimb.demo.common.constants.BeanConstants.SPECIAL_DAY;
import static com.cimb.demo.common.constants.BeanConstants.SPECIAL_MONTH;
import static com.cimb.demo.common.constants.BeanConstants.SPECIAL_YEAR;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;
import static com.jayway.jsonpath.JsonPath.parse;

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

    @Getter(AccessLevel.PRIVATE)
    private static final List<String> specialComponents = List.of("day");
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private DocumentContext documentContext;
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private ApplicationContext applicationContext;
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private List<PayloadMapValue<?>> payloadMapValues = new ArrayList<>();

    public PayloadDTO(String formId, String data, Object component, ApplicationContext applicationContext) {
        this.formId = formId;
        this.data = data;
        this.documentContext = parse(component);

        this.type = getJsonValue(this.documentContext, "$.%s".formatted(KEY_TYPE), String.class);
        this.customMsg = getJsonValue(this.documentContext,  "$.%s.%s".formatted(KEY_VALIDATE, KEY_CUSTOM_MESSAGE), String.class);
        this.key = getJsonValue(this.documentContext,  "$.%s".formatted(KEY), String.class);
        this.applicationContext = applicationContext;

        if (specialComponents.contains(this.getType())) {
            payloadMapValues = getPayloadMapValueSpecial();
        }
    }

    private List<PayloadMapValue<?>> getPayloadMapValueSpecial() {
        return switch (this.type) {
            case "day" -> List.of(
                    new PayloadMapValue<>(SPECIAL_DAY + KEY_REQUIRED, PayloadDTO::setRequired, applicationContext, documentContext, Boolean.class),
                    new PayloadMapValue<>(SPECIAL_MONTH + KEY_REQUIRED, PayloadDTO::setRequired, applicationContext, documentContext, Boolean.class),
                    new PayloadMapValue<>(SPECIAL_YEAR + KEY_REQUIRED, PayloadDTO::setRequired, applicationContext, documentContext, Boolean.class)
            );
            default -> Collections.emptyList();
        };
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

    public PayloadDTO allValidator() {
        this.addValidator(PayloadMapValue.all(this.applicationContext, this.documentContext));
        return this;
    }
}
