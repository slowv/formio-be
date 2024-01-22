package com.cimb.demo.service.validation.dto;

import com.cimb.demo.service.validation.Validator;

import java.util.List;

public record PairPayloadValidation(PayloadDTO key, List<Validator> validators) {
}
