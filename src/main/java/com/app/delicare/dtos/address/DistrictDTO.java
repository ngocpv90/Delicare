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
public class DistrictDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("districtCode")
    private String districtCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("districtName")
    private String districtName;
    @JsonProperty("provinceId")
    private Long provinceId;
}
