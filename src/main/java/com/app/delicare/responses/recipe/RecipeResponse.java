package com.app.delicare.responses.recipe;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RecipeResponse extends BaseResponse{
    private Long id;
    private String recipeCode;
    private String recipeName;
    private String recipeType;
}
