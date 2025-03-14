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
public class UnitDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("unitCode")
    private String unitCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("unitName")
    private String unitName;
    @JsonProperty("unitType")
    private String unitType;
}
