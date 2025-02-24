package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.RecipeIngredientDTO;
import com.app.delicare.entitys.Ingredient;
import com.app.delicare.entitys.Recipe;
import com.app.delicare.entitys.RecipeIngredient;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.IngredientRepository;
import com.app.delicare.repositories.RecipeRepository;
import com.app.delicare.responses.IngredientResponse;
import com.app.delicare.responses.RecipeIngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipeIngredientMapper extends BaseMapper {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;

    public RecipeIngredient mapEntityToModel(RecipeIngredientDTO recipeIngredientDTO){
        RecipeIngredient recipeIngredient = RecipeIngredient.builder()
                .build();

        if(!CommonUtils.isNullOrEmpty(recipeIngredientDTO.getRecipeId())){
            Recipe foodRecipe = recipeRepository.getReferenceById(recipeIngredientDTO.getIngredientId());
            recipeIngredient.setRecipe(foodRecipe);
        }

        if(!CommonUtils.isNullOrEmpty(recipeIngredientDTO.getIngredientId())){
            Ingredient ingredient = ingredientRepository.getReferenceById(recipeIngredientDTO.getIngredientId());
            recipeIngredient.setIngredient(ingredient);
        }
        recipeIngredient.setStatus(recipeIngredientDTO.getStatus());
        return recipeIngredient;
    }

    public RecipeIngredientResponse mapResponseToEntity(RecipeIngredient recipeIngredient){
        RecipeIngredientResponse recipeIngredientResponse = RecipeIngredientResponse.builder()
                .id(recipeIngredient.getId())
                .build();

        Optional.ofNullable(recipeIngredient)
                .map(RecipeIngredient::getRecipe)
                .ifPresent(recipe -> {
                    recipeIngredientResponse.setRecipeResponse(recipeMapper.mapResponseToEntity(recipeIngredient.getRecipe()));
                });
        Optional.ofNullable(recipeIngredient)
                .map(RecipeIngredient::getIngredient)
                .ifPresent(ingredient -> {
                    recipeIngredientResponse.setIngredientResponse(ingredientMapper.mapResponseToEntity(recipeIngredient.getIngredient()));
                });
        Optional.ofNullable(recipeIngredient)
                .map(RecipeIngredient::getCreatedByUser)
                .ifPresent(user -> {
                    recipeIngredientResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(recipeIngredient.getCreatedByUser()));
                });
        Optional.ofNullable(recipeIngredient)
                .map(RecipeIngredient::getModifiedByUser)
                .ifPresent(user -> {
                    recipeIngredientResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(recipeIngredient.getModifiedByUser()));
                });
        recipeIngredientResponse.setDeleted(recipeIngredient.getDeleted());
        recipeIngredientResponse.setDescription(recipeIngredient.getDescription());
        recipeIngredientResponse.setCreatedAt(recipeIngredient.getCreatedAt());
        recipeIngredientResponse.setCreatedAt(recipeIngredient.getCreatedAt());
        recipeIngredientResponse.setModifiedAt(recipeIngredient.getModifiedAt());
        recipeIngredientResponse.setStatus(recipeIngredient.getStatus());
        return recipeIngredientResponse;
    }
}
