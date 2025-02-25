package com.app.delicare.service.implement;

import com.app.delicare.dtos.recipe.RecipeDTO;
import com.app.delicare.responses.recipe.RecipeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRecipeService {
    RecipeResponse createRecipe(RecipeDTO recipeDTO);
    List<RecipeResponse> getAllRecipe();
    Page<RecipeResponse> getListRecipe(PageRequest pageRequest);
    RecipeResponse updateRecipe(Long id, RecipeDTO recipeDTO);
    RecipeResponse getRecipeById(Long id);
    void deleteRecipe(Long id);
}
