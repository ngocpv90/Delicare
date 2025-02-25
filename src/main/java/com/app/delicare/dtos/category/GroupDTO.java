package com.app.delicare.dtos.category;

import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("groupCode")
    private String groupCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("groupName")
    private String groupName;
    @JsonProperty("status")
    private int status;
    @JsonProperty("departmentId")
    private Long departmentId;
}
