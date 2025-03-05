package com.app.delicare.dtos.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("deleted")
    private int deleted;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private int status;
    private Long createdById;
    private Long modifiedById;
}
