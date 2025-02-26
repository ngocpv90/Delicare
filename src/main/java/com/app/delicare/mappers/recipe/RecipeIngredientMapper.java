package com.app.delicare.mappers.recipe;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.recipe.RecipeIngredientDTO;
import com.app.delicare.entitys.ingredient.Ingredient;
import com.app.delicare.entitys.recipe.Recipe;
import com.app.delicare.entitys.recipe.RecipeIngredient;
import com.app.delicare.mappers.IngredientMapper;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.IngredientRepository;
import com.app.delicare.repositories.ingredient.RecipeRepository;
import com.app.delicare.responses.recipe.RecipeIngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public List<RecipeIngredient> mapEntityToModel(List<RecipeIngredientDTO> recipeIngredientDTOList){
        return recipeIngredientDTOList.stream().map(recipeIngredientDTO -> {
            return mapEntityToModel(recipeIngredientDTO);
        }).toList();
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
