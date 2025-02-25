package com.app.delicare.dtos.recipe;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("recipeCode")
    private String recipeCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("recipeName")
    private String recipeName;
    @JsonProperty("recipeType")
    private String recipeType;
}
