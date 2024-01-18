package com.cimb.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    private String display;

    private String action;

    private List<String> tags = new ArrayList<>();

    private List<Object> components = new ArrayList<>();

    private List<SubmissionAccessDTO> submissionAccess = new ArrayList<>();

    private String settings;

    private String properties;
}
