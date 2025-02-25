package com.app.delicare.mappers;

import com.app.delicare.dtos.recipe.RecipeDTO;
import com.app.delicare.entitys.recipe.Recipe;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.recipe.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipeMapper extends BaseMapper {

    public Recipe mapEntityToModel(RecipeDTO recipeDTO){
        Recipe foodRecipe = Recipe.builder()
                .recipeCode(recipeDTO.getRecipeCode())
                .recipeName(recipeDTO.getRecipeName())
                .recipeType(recipeDTO.getRecipeType())
                .build();

        foodRecipe.setStatus(recipeDTO.getStatus());
        return foodRecipe;
    }

    public RecipeResponse mapResponseToEntity(Recipe recipe){
        RecipeResponse recipeResponse = RecipeResponse.builder()
                .id(recipe.getId())
                .recipeCode(recipe.getRecipeCode())
                .recipeName(recipe.getRecipeName())
                .recipeType(recipe.getRecipeType())
                .build();

        Optional.ofNullable(recipe)
                .map(Recipe::getCreatedByUser)
                .ifPresent(user -> {
                    recipeResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(recipe.getCreatedByUser()));
                });
        Optional.ofNullable(recipe)
                .map(Recipe::getModifiedByUser)
                .ifPresent(user -> {
                    recipeResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(recipe.getModifiedByUser()));
                });
        recipeResponse.setDeleted(recipe.getDeleted());
        recipeResponse.setDescription(recipe.getDescription());
        recipeResponse.setCreatedAt(recipe.getCreatedAt());
        recipeResponse.setCreatedAt(recipe.getCreatedAt());
        recipeResponse.setModifiedAt(recipe.getModifiedAt());
        recipeResponse.setStatus(recipe.getStatus());
        return recipeResponse;
    }
}
