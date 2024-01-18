package com.cimb.demo.service.dto;

import com.cimb.demo.entity.enums.AccessType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubmissionAccessDTO {
    private AccessType type;
    private List<String> roles = new ArrayList<>();
}
