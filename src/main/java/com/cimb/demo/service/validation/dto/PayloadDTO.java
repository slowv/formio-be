package com.cimb.demo.service.validation.dto;

import com.google.gson.JsonObject;
import lombok.Data;

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
    private JsonObject data;
    private String customMsg;
    private int min;
    private int max;

    public void setRequired(final String required) {
        this.required = Boolean.parseBoolean(required);
    }

    public void setMaxLength(final String maxLength) {
        this.maxLength = Integer.parseInt(maxLength);
    }

    public void setMinLength(final String minLength) {
        this.minLength = Integer.parseInt(minLength);
    }

    public void setMin(final String min) {
        this.min = Integer.parseInt(min);
    }

    public void setMax(final String max) {
        this.max = Integer.parseInt(max);
    }
}
