package com.cimb.demo.component;

import com.cimb.demo.common.utils.JsonUtil;
import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.service.validation.EndWithValidator;
import com.cimb.demo.service.validation.MaxLengthValidator;
import com.cimb.demo.service.validation.MaxValidator;
import com.cimb.demo.service.validation.MinLengthValidator;
import com.cimb.demo.service.validation.MinValidator;
import com.cimb.demo.service.validation.PatternValidator;
import com.cimb.demo.service.validation.RequiredValidator;
import com.cimb.demo.service.validation.StartWithValidator;
import com.cimb.demo.service.validation.UniqueValidator;
import com.cimb.demo.service.validation.Validator;
import com.cimb.demo.service.validation.dto.PairPayloadValidation;
import com.cimb.demo.service.validation.dto.PairResultValidation;
import com.cimb.demo.service.validation.dto.PayloadDTO;
import com.cimb.demo.service.validation.dto.ValidationResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.cimb.demo.common.utils.JsonUtil.getJsonArray;
import static com.cimb.demo.common.utils.JsonUtil.getJsonObject;

@Component
@RequiredArgsConstructor
public class ValidatorDataExtractor {
    private final Gson gson = new Gson();

    // Inject all form validator
    private final RequiredValidator requiredValidator;
    private final UniqueValidator uniqueValidator;
    private final MaxLengthValidator maxLengthValidator;
    private final MinLengthValidator minLengthValidator;
    private final PatternValidator patternValidator;
    private final StartWithValidator startWithValidator;
    private final EndWithValidator endWithValidator;
    private final MinValidator minValidator;
    private final MaxValidator maxValidator;

    private final Map<String, Validator> validators = new HashMap<>();
    private final String KEY_PREFIX = "prefix";
    private final String KEY_SUFFIX = "suffix";
    private final String KEY_PATTERN = "pattern";
    private final String KEY_REQUIRED = "required";
    private final String KEY_MAX_LENGTH = "maxLength";
    private final String KEY_MIN_LENGTH = "minLength";
    private final String KEY_TYPE = "type";

    private final String KEY_VALIDATE = "validate";
    private final String KEY_CUSTOM_MESSAGE = "customMessage";

    @PostConstruct
    public void init() {
        validators.put(KEY_PREFIX, startWithValidator);
        validators.put(KEY_SUFFIX, endWithValidator);
        validators.put(KEY_PATTERN, patternValidator);
        validators.put(KEY_REQUIRED, requiredValidator);
        validators.put(KEY_MAX_LENGTH, maxLengthValidator);
        validators.put(KEY_MIN_LENGTH, minLengthValidator);
    }

    /**
     * @param submission is data submitted, read more info here {@link SubmissionEntity}
     */
    public Map<String, List<ValidationResult>> extract(final SubmissionEntity submission) {
        final var data = getJsonObject(submission.getData());
        final var components = getJsonArray(submission.getForm());
        final var payloads = payloads(components, data, submission.getForm().getId());

        return payloads.entrySet()
                .stream()
                .map(entry -> {
                    final var payload = entry.getKey();
                    final var validators = entry.getValue();
                    return new PairResultValidation(
                            payload.getKey(),
                            validators
                                    .stream()
                                    .map(validator -> validator.execute(payload))
                                    .filter(Objects::nonNull)
                                    .toList());
                })
                .filter(pairResult -> !CollectionUtils.isEmpty(pairResult.validationResults()))
                .collect(Collectors.toMap(PairResultValidation::key, PairResultValidation::validationResults));
    }

    public Map<PayloadDTO, List<Validator>> payloads(JsonArray components, JsonObject data, String formId) {
        return StreamSupport.stream(components.spliterator(), false)
                .map(component -> {
                    final var payload = new PayloadDTO();
                    payload.setData(data);
                    payload.setFormId(formId);
                    final List<Validator> validators = new ArrayList<>();

                    // Extract validation json
                    if (component.isJsonObject()) {
                        final var jsonObject = component.getAsJsonObject();
                        final var componentKey = Objects.requireNonNull(getJsonObject(jsonObject, "key")).getAsString();
                        payload.setKey(componentKey);

                        setValue(jsonObject, KEY_PREFIX, PayloadDTO::setPrefix, payload, validators);
                        setValue(jsonObject, KEY_SUFFIX, PayloadDTO::setSuffix, payload, validators);
                        final var type = getJsonObject(jsonObject, "type");
                        if (!ObjectUtils.isEmpty(type)) {
                            payload.setType(type.getAsString());
                        }

                        final var validateJsonObject = getJsonObject(jsonObject, KEY_VALIDATE);
                        if (!ObjectUtils.isEmpty(validateJsonObject)) {
                            setValue(validateJsonObject, KEY_PATTERN, PayloadDTO::setPattern, payload, validators);
                            setValue(validateJsonObject, KEY_REQUIRED, PayloadDTO::setRequired, payload, validators);
                            setValue(validateJsonObject, KEY_MAX_LENGTH, PayloadDTO::setMaxLength, payload, validators);
                            setValue(validateJsonObject, KEY_MIN_LENGTH, PayloadDTO::setMinLength, payload, validators);
                            final var customMessage = getJsonObject(validateJsonObject.getAsJsonObject(), KEY_CUSTOM_MESSAGE);
                            if (!ObjectUtils.isEmpty(customMessage)) {
                                payload.setCustomMsg(customMessage.getAsString());
                            }

                        }
                    }
                    return new PairPayloadValidation(payload, validators);
                })
                .collect(Collectors.toMap(PairPayloadValidation::key, PairPayloadValidation::validators));
    }


    @SuppressWarnings("unchecked")
    private <T, R> void setValue(JsonElement jsonObject, String key, BiConsumer<T, R> setter, T payload, List<Validator> validators) {
        final var valueJson = getJsonObject(jsonObject.getAsJsonObject(), key);
        if (ObjectUtils.isEmpty(valueJson)) return;
        if (valueJson.isJsonPrimitive()) {
            setter.accept(payload, (R) valueJson.getAsString());
        }
        if (this.validators.containsKey(key)) {
            validators.add(this.validators.get(key));
        }
    }

    @SuppressWarnings("unchecked")
    private <T, R> void setValue(JsonElement jsonObject, String key, BiConsumer<T, R> setter, T payload) {
        final var valueJson = getJsonObject(jsonObject.getAsJsonObject(), key);
        if (ObjectUtils.isEmpty(valueJson)) return;
        if (valueJson.isJsonPrimitive()) {
            setter.accept(payload, (R) valueJson.getAsString());
        }
    }
}
