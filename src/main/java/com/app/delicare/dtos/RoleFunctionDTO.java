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
public class RoleFunctionDTO extends BaseDTO{
    @JsonProperty("roleId")
    private Long roleId;
    @JsonProperty("functionId")
    private Long functionId;
}
