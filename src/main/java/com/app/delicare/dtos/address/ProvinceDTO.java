package com.app.delicare.dtos.address;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("provinceCode")
    private String provinceCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("provinceName")
    private String provinceName;
}
