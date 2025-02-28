package com.app.delicare.dtos.category;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("actionCode")
    private String actionCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("actionName")
    private String actionName;
    @JsonProperty("isDisplay")
    private int isDisplay;
    @JsonProperty("sort")
    private Long sort;
}
