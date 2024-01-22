package com.cimb.demo.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeanConstants {
    // Bean name Validators
    public static final String FORM_REQUIRED_VALIDATOR = "formRequiredValidator";
    public static final String FORM_UNIQUE_VALIDATOR = "formUniqueValidator";
    public static final String FORM_MAX_LENGTH_VALIDATOR = "formMaxLengthValidator";
    public static final String FORM_MIN_LENGTH_VALIDATOR = "formMinLengthValidator";
    public static final String FORM_PATTERN_VALIDATOR = "formPatternValidator";
    public static final String FORM_START_WITH_VALIDATOR = "formStartWithValidator";
    public static final String FORM_END_WITH_VALIDATOR = "formENdWithValidator";
}
