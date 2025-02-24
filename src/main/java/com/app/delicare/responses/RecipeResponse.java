package com.app.delicare.responses;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse extends BaseResponse{
    private Long id;
    private String recipeCode;
    private String recipeName;
    private String recipeType;
}
