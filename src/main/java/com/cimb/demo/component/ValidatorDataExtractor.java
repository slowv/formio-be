package com.cimb.demo.component;

import com.cimb.demo.entity.FormEntity;
import com.cimb.demo.entity.SubmissionEntity;
import com.cimb.demo.service.validation.RequiredValidator;
import com.cimb.demo.service.validation.UniqueValidator;
import com.cimb.demo.service.validation.ValidationResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidatorDataExtractor {
    private final Gson gson = new Gson();

    // Inject all form validator
    private final RequiredValidator requiredValidator;
    private final UniqueValidator uniqueValidator;

    /**
     * @param submission is data submitted, read more info here {@link SubmissionEntity}
     */
    public List<ValidationResult> extract(final SubmissionEntity submission) {
        final var data = getData(submission.getData());
        final var components = getComponents(submission.getForm());


        // Impl code get validator by setting component then execute return result.
        // impl code here.
        return new ArrayList<>();
    }

    private JsonObject getData(String data) {
        return gson.fromJson(data, JsonObject.class);
    }

    private JsonArray getComponents(final FormEntity form) {
        return gson.fromJson(form.getComponents(), JsonArray.class);
    }

}
