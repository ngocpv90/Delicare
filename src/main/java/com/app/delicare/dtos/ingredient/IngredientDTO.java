package com.app.delicare.dtos.ingredient;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO extends BaseDTO {
    @NotEmpty(message = "code is not null")
    @JsonProperty("foodIngredientCode")
    private String ingredientCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("foodIngredientName")
    private String ingredientName;
    @JsonProperty("unitId")
    private Long unitId;
}
