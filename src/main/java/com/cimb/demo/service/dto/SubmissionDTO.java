package com.cimb.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubmissionDTO extends AbstractAuditingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank
    private String formId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String owner;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RoleDTO> role = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> access = new ArrayList<>();

    private Object metadata;

    @NotNull
    private Object data;
}
