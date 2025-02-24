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
public class StageDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("stageCode")
    private String stageCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("stateName")
    private String stateName;
    @JsonProperty("stageType")
    private String stateType;
}
