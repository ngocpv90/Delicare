package com.app.delicare.dtos;

import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("departmentCode")
    private String departmentCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("departmentName")
    private String departmentName;
}
