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
public class CategoryDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("code")
    private String code;
    @NotEmpty(message = "name is not null")
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
}
