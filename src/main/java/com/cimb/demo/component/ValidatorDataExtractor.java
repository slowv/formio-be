package com.cimb.demo.component;

import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.service.validation.Validator;
import com.cimb.demo.service.validation.dto.PairResultValidation;
import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.jayway.jsonpath.TypeRef;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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

@Component
@RequiredArgsConstructor
public class ValidatorDataExtractor {

    private final ApplicationContext applicationContext;

    /**
     * @param submission is data submitted, read more info here {@link SubmissionEntity}
     */
    public Map<String, List<ValidationResult>> extract(final SubmissionEntity submission) {
        return parse(submission.getForm().getComponents())
                .read("@[?]", new TypeRef<List<String>>() {})
                .stream()
                .map(component -> getPayload(component, submission.getForm().getId(), submission.getData()))
                .map(payload -> new PairResultValidation(
                        payload.getKey(),
                        payload.validate()))
                .filter(pairResult -> !CollectionUtils.isEmpty(pairResult.validationResults()))
                .collect(Collectors.toMap(PairResultValidation::key, PairResultValidation::validationResults));
    }

    private PayloadDTO getPayload(Object component, String formId, Object data) {
        final var payload = new PayloadDTO();
        final var context = parse(component);
        payload.setFormId(formId);

        setValue(KEY_PREFIX, PayloadDTO::setPrefix, payload, getJsonValue(context, "@.%s".formatted(KEY_PREFIX), String.class));
        setValue(KEY_SUFFIX, PayloadDTO::setSuffix, payload, getJsonValue(context, "@.%s".formatted(KEY_SUFFIX), String.class));
        setValue(KEY_TYPE, PayloadDTO::setType, payload, getJsonValue(context, "@.%s".formatted(KEY_TYPE), String.class));
        setValue(KEY, PayloadDTO::setKey, payload, getJsonValue(context, "@.%s".formatted(KEY), String.class));

        setValue(KEY_REQUIRED, PayloadDTO::setRequired, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_REQUIRED), Boolean.class));
        setValue(KEY_PATTERN, PayloadDTO::setPattern, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_PATTERN), String.class));
        setValue(KEY_MAX_LENGTH, PayloadDTO::setMaxLength, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_MAX_LENGTH), Integer.class));
        setValue(KEY_MIN_LENGTH, PayloadDTO::setMinLength, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_MIN_LENGTH), Integer.class));
        setValue(KEY_MIN, PayloadDTO::setMin, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_MIN), Integer.class));
        setValue(KEY_MAX, PayloadDTO::setMax, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_MAX), Integer.class));
        setValue(KEY_CUSTOM_MESSAGE, PayloadDTO::setCustomMsg, payload, getJsonValue(context, "@.%s.%s".formatted(KEY_VALIDATE, KEY_CUSTOM_MESSAGE), String.class));

        return payload;
    }

    private <R> void setValue(String key, BiConsumer<PayloadDTO, R> setter, PayloadDTO payload, R data) {
        if (ObjectUtils.isEmpty(data)) return;
        if (data instanceof String str && ObjectUtils.isEmpty(str)) return;

        setter.accept(payload, data);
        if (applicationContext.containsBean(key + BEAN_VALIDATOR)) {
            payload.addValidator(applicationContext.getBean(key + BEAN_VALIDATOR, Validator.class));
        }
    }
}
