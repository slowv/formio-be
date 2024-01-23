package com.cimb.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionDTO extends AbstractAuditingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private float priority;
    private String name;
    private String title;
    @NotBlank
    private String formId;
    private List<String> handler;
    private List<String> method;
    private Object settings;
    private Object condition;
    private String submit;
    private String state;
}
