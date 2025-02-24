package com.app.delicare.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseDTO {
    @JsonProperty("deleted")
    private int deleted;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private int status;
    private Long createdById;
    private Long modifiedById;
}
