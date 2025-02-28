package com.app.delicare.dtos.category;

import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FunctionActionDTO extends BaseDTO {
    @JsonProperty("functionId")
    private Long functionId;
    @JsonProperty("actionId")
    private Long actionId;
}
