package com.app.delicare.responses.recipe;

import com.app.delicare.responses.IngredientResponse;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RecipeIngredientResponse extends BaseResponse {
    private Long id;
    private RecipeResponse recipeResponse;
    private IngredientResponse ingredientResponse;
}
