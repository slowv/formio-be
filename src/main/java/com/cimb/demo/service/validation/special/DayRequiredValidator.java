package com.cimb.demo.service.validation.special;

import com.cimb.demo.service.validation.RequiredValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.cimb.demo.common.constants.BeanConstants.FORM_REQUIRED_VALIDATOR;
import static com.cimb.demo.common.constants.BeanConstants.KEY_REQUIRED;

@Slf4j
@Component("day" + FORM_REQUIRED_VALIDATOR)
public class DayRequiredValidator extends RequiredValidator {
    public DayRequiredValidator() {
        this.jsonPath = "$.fields.day.%s".formatted(KEY_REQUIRED);
    }
}
