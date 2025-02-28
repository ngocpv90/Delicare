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
public class FunctionDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("functionCode")
    private String functionCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("functionName")
    private String functionName;
    @JsonProperty("parentId")
    private Long parentId;
    @JsonProperty("iconPath")
    private String iconPath;
    @JsonProperty("isDisplay")
    private Long isDisplay;
    @JsonProperty("orderDisplay")
    private String orderDisplay;
}
