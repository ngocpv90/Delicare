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
public class TitleDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("titleCode")
    private String titleCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("titleName")
    private String titleName;
    @JsonProperty("titleType")
    private int titleType;
}
