package com.app.delicare.service.recipe;

import com.app.delicare.dtos.recipe.RecipeDTO;
import com.app.delicare.entitys.recipe.Recipe;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.recipe.RecipeMapper;
import com.app.delicare.repositories.ingredient.RecipeRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.recipe.RecipeResponse;
import com.app.delicare.specification.recipe.RecipeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RecipeService implements IRecipeService {
    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    public RecipeResponse createRecipe(RecipeDTO recipeDTO) {
        try{
            Recipe foodRecipe = recipeMapper.mapEntityToModel(recipeDTO);
            recipeRepository.save(foodRecipe);
            recipeRepository.flush();
            return recipeMapper.mapResponseToEntity(foodRecipe);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RecipeResponse> getAllRecipe() {
        Specification<Recipe> specification = Specification.where(RecipeSpecification.isNotDeleted());
        return recipeRepository.findAll(specification).stream().map(foodRecipe -> {
                    return  recipeMapper.mapResponseToEntity(foodRecipe);
                })
                .toList();
    }

    @Override
    public Page<RecipeResponse> getListRecipe(PageRequest pageRequest) {
        Specification<Recipe> specification = Specification.where(RecipeSpecification.isNotDeleted());
        return recipeRepository.findAll(specification, pageRequest).map(foodRecipe -> {
            return recipeMapper.mapResponseToEntity(foodRecipe);
        });
    }

    @Override
    public RecipeResponse updateRecipe(Long id, RecipeDTO recipeDTO) {
        User userModified = userRepository.getReferenceById(recipeDTO.getModifiedById());
        Recipe foodIngredient = recipeMapper.mapEntityToModel(recipeDTO);
        foodIngredient.setId(id);
        foodIngredient.setModifiedByUser(userModified);
        recipeRepository.save(foodIngredient);
        return recipeMapper.mapResponseToEntity(foodIngredient);
    }

    @Override
    public RecipeResponse getRecipeById(Long id) {
        Recipe foodRecipe = recipeRepository.getReferenceById(id);
        return recipeMapper.mapResponseToEntity(foodRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {

    }
}
