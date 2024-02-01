package com.cimb.demo.service.validation.dto;

import com.cimb.demo.service.validation.Validator;
import com.jayway.jsonpath.DocumentContext;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.function.BiConsumer;

import static com.cimb.demo.common.constants.BeanConstants.BEAN_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MAX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MAX_LENGTH;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MIN;
import static com.cimb.demo.common.constants.BeanConstants.KEY_MIN_LENGTH;
import static com.cimb.demo.common.constants.BeanConstants.KEY_PATTERN;
import static com.cimb.demo.common.constants.BeanConstants.KEY_PREFIX;
import static com.cimb.demo.common.constants.BeanConstants.KEY_REQUIRED;
import static com.cimb.demo.common.constants.BeanConstants.KEY_SUFFIX;

@Getter
public class PayloadMapValue<R> {
    private final String key;
    private final BiConsumer<PayloadDTO, R> setter;
    private final ApplicationContext context;
    private final DocumentContext documentContext;
    private final Class<R> clazz;

    public PayloadMapValue(final String key, final BiConsumer<PayloadDTO, R> setter, final ApplicationContext context,  DocumentContext documentContext, Class<R> clazz) {
        this.key = key;
        this.setter = setter;
        this.context = context;
        this.documentContext = documentContext;
        this.clazz = clazz;
    }

    public static List<PayloadMapValue<?>> all(ApplicationContext applicationContext, DocumentContext documentContext) {
        return List.of(
                new PayloadMapValue<>(KEY_PREFIX, PayloadDTO::setPrefix, applicationContext, documentContext, String.class),
                new PayloadMapValue<>(KEY_SUFFIX, PayloadDTO::setSuffix, applicationContext, documentContext, String.class),
                new PayloadMapValue<>(KEY_REQUIRED, PayloadDTO::setRequired, applicationContext, documentContext, Boolean.class),
                new PayloadMapValue<>(KEY_PATTERN, PayloadDTO::setPattern, applicationContext, documentContext, String.class),
                new PayloadMapValue<>(KEY_MAX_LENGTH, PayloadDTO::setMaxLength, applicationContext, documentContext, Integer.class),
                new PayloadMapValue<>(KEY_MIN_LENGTH, PayloadDTO::setMinLength, applicationContext, documentContext, Integer.class),
                new PayloadMapValue<>(KEY_MIN, PayloadDTO::setMin, applicationContext, documentContext, Integer.class),
                new PayloadMapValue<>(KEY_MAX, PayloadDTO::setMax, applicationContext, documentContext, Integer.class)
        );
    }

    public Validator setValue(PayloadDTO payload) {
        final var applicationContext = this.getContext();
        final var validator = applicationContext.getBean(this.key + BEAN_VALIDATOR, Validator.class);

        final var data = validator.getValue(this.documentContext, this.clazz);
        if (ObjectUtils.isEmpty(data)) return null;
        if (data instanceof String str && ObjectUtils.isEmpty(str)) return null;
        this.setter.accept(payload, data);
        return validator;
    }
}
