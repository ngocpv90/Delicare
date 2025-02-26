package com.app.delicare.service.recipe;

import com.app.delicare.dtos.recipe.RecipeIngredientDTO;
import com.app.delicare.responses.recipe.RecipeIngredientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRecipeIngredientService{
    RecipeIngredientResponse createRecipe(RecipeIngredientDTO recipeIngredientDTO);
    List<RecipeIngredientResponse> getAllRecipe();
    Page<RecipeIngredientResponse> getListRecipe(PageRequest pageRequest);
    RecipeIngredientResponse updateRecipe(Long id, RecipeIngredientDTO recipeIngredientDTO);
    RecipeIngredientResponse getRecipeById(Long id);
    void deleteRecipe(Long id);
}
