package com.cimb.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormDTO extends AbstractAuditingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    private String code;

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

    private List<Object> submissionAccess = new ArrayList<>();

    private String settings;

    private String properties;
}
