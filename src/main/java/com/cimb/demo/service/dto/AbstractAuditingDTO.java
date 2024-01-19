package com.cimb.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class AbstractAuditingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant lastModifiedDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastModifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant deletedDate;
}
