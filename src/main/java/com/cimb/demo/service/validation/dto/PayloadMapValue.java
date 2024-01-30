package com.cimb.demo.service.validation.dto;

import com.cimb.demo.service.validation.Validator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jayway.jsonpath.DocumentContext;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.function.BiConsumer;

import static com.cimb.demo.common.constants.BeanConstants.BEAN_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY;
import static com.cimb.demo.common.constants.BeanConstants.KEY_CUSTOM_MESSAGE;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MAX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MAX_LENGTH;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MIN;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MIN_LENGTH;
import static com.cimb.demo.common.constants.BeanConstants.KEY_PATTERN;
import static com.cimb.demo.common.constants.BeanConstants.KEY_PREFIX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_REQUIRED;
import static com.cimb.demo.common.constants.BeanConstants.KEY_SUFFIX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_TYPE;
import static com.cimb.demo.common.constants.BeanConstants.KEY_VALIDATE;
import static com.cimb.demo.common.utils.JsonUtil.getJsonValue;
import static com.jayway.jsonpath.JsonPath.parse;

@Getter
public class PayloadMapValue<R> {
    private final String key;
    private final String jsonPath;
    private final BiConsumer<PayloadDTO, R> setter;
    @JsonIgnore
    private final ApplicationContext context;
    @JsonIgnore
    private final DocumentContext documentContext;
    private final Class<R> clazz;

    public PayloadMapValue(final String key, final String jsonPath, final BiConsumer<PayloadDTO, R> setter, final ApplicationContext context, Object component, Class<R> clazz) {
        this.key = key;
        this.jsonPath = jsonPath;
        this.setter = setter;
        this.context = context;
        this.documentContext = parse(component);
        this.clazz = clazz;
    }

    public static List<PayloadMapValue<?>> all(ApplicationContext applicationContext, Object component) {
        return List.of(
                new PayloadMapValue<>(KEY_PREFIX, "$.%s".formatted(KEY_PREFIX), PayloadDTO::setPrefix, applicationContext, component, String.class),
                new PayloadMapValue<>(KEY_SUFFIX, "$.%s".formatted(KEY_SUFFIX), PayloadDTO::setSuffix, applicationContext, component, String.class),
                new PayloadMapValue<>(KEY_TYPE, "$.%s".formatted(KEY_TYPE), PayloadDTO::setType, applicationContext, component, String.class),
                new PayloadMapValue<>(KEY, "$.%s".formatted(KEY), PayloadDTO::setKey, applicationContext, component, String.class),
                new PayloadMapValue<>(KEY_REQUIRED, "$.%s.%s".formatted(KEY_VALIDATE, KEY_REQUIRED), PayloadDTO::setRequired, applicationContext, component, Boolean.class),
                new PayloadMapValue<>(KEY_PATTERN, "$.%s.%s".formatted(KEY_VALIDATE, KEY_PATTERN), PayloadDTO::setPattern, applicationContext, component, String.class),
                new PayloadMapValue<>(KEY_MAX_LENGTH, "$.%s.%s".formatted(KEY_VALIDATE, KEY_MAX_LENGTH), PayloadDTO::setMaxLength, applicationContext, component, Integer.class),
                new PayloadMapValue<>(KEY_MIN_LENGTH, "$.%s.%s".formatted(KEY_VALIDATE, KEY_MIN_LENGTH), PayloadDTO::setMinLength, applicationContext, component, Integer.class),
                new PayloadMapValue<>(KEY_MIN, "$.%s.%s".formatted(KEY_VALIDATE, KEY_MIN), PayloadDTO::setMin, applicationContext, component, Integer.class),
                new PayloadMapValue<>(KEY_MAX, "$.%s.%s".formatted(KEY_VALIDATE, KEY_MAX), PayloadDTO::setMax, applicationContext, component, Integer.class),
                new PayloadMapValue<>(KEY_CUSTOM_MESSAGE, "$.%s.%s".formatted(KEY_VALIDATE, KEY_CUSTOM_MESSAGE), PayloadDTO::setCustomMsg, applicationContext, component, String.class)
        );
    }

    public Validator setValue(PayloadDTO payload) {
        final var data = getJsonValue(this.getDocumentContext(), this.getJsonPath(), this.getClazz());
        if (ObjectUtils.isEmpty(data)) return null;
        if (data instanceof String str && ObjectUtils.isEmpty(str)) return null;

        this.setter.accept(payload, data);
        final var applicationContext = this.getContext();
        if (applicationContext.containsBean(this.key + BEAN_VALIDATOR)) {
            return applicationContext.getBean(this.key + BEAN_VALIDATOR, Validator.class);
        }
        return null;
    }
}
