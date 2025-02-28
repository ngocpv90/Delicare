package com.app.delicare.dtos.address;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WardDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("wardCode")
    private String wardCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("wardName")
    private String wardName;
    @JsonProperty("districtId")
    private Long districtId;
}
