package com.cimb.demo.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeanConstants {

    public static final String KEY = "key";
    public static final String KEY_PREFIX = "prefix";
    public static final String KEY_SUFFIX = "suffix";
    public static final String KEY_PATTERN = "pattern";
    public static final String KEY_REQUIRED = "required";
    public static final String KEY_MAX_LENGTH = "maxLength";
    public static final String KEY_MIN_LENGTH = "minLength";
    public static final String KEY_TYPE = "type";
    public static final String KEY_UNIQUE = "unique";
    public static final String KEY_MIN = "min";
    public static final String KEY_MAX = "max";

    public static final String KEY_VALIDATE = "validate";
    public static final String KEY_CUSTOM_MESSAGE = "customMessage";

    // Key name Bean
    public static final String BEAN_VALIDATOR = "formValidator";
    // Bean name Validators
    public static final String FORM_REQUIRED_VALIDATOR = KEY_REQUIRED + BEAN_VALIDATOR;
    public static final String FORM_MIN_VALIDATOR = KEY_MIN + BEAN_VALIDATOR;
    public static final String FORM_MAX_VALIDATOR = KEY_MAX + BEAN_VALIDATOR;
    public static final String FORM_UNIQUE_VALIDATOR = KEY_UNIQUE + BEAN_VALIDATOR;
    public static final String FORM_MAX_LENGTH_VALIDATOR = KEY_MAX_LENGTH + BEAN_VALIDATOR;
    public static final String FORM_MIN_LENGTH_VALIDATOR = KEY_MIN_LENGTH + BEAN_VALIDATOR;
    public static final String FORM_PATTERN_VALIDATOR = KEY_PATTERN + BEAN_VALIDATOR;
    public static final String FORM_START_WITH_VALIDATOR = KEY_PREFIX + BEAN_VALIDATOR;
    public static final String FORM_END_WITH_VALIDATOR = KEY_SUFFIX + BEAN_VALIDATOR;


}
