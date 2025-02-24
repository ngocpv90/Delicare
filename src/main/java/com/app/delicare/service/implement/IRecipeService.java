package com.app.delicare.service.implement;

import com.app.delicare.dtos.RecipeDTO;
import com.app.delicare.responses.RecipeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRecipeService {
    RecipeResponse createFoodRecipe(RecipeDTO foodRecepeDTO);
    List<RecipeResponse> getAllFoodRecipen();
    Page<RecipeResponse> getListFoodRecipe(PageRequest pageRequest);
    RecipeResponse updateFoodRecipe(Long id, RecipeDTO foodRecepeDTO);
    RecipeResponse getFoodRecipeById(Long id);
    void deleteFoodRecipe(Long id);
}
