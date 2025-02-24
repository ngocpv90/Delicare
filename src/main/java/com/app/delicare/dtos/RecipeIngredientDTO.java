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
public class RecipeIngredientDTO extends BaseDTO{
    @JsonProperty("recipeId")
    private Long recipeId;
    @JsonProperty("ingredientId")
    private Long ingredientId;
}
