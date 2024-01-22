package com.cimb.demo.service.validation.dto;

import java.util.List;

public record PairResultValidation(String key, List<ValidationResult> validationResults) {
}
