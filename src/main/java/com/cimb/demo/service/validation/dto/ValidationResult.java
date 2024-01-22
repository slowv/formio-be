package com.cimb.demo.service.validation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ValidationResult(String message, @JsonIgnore boolean result) {
}
