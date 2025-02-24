package com.app.delicare.service;

import com.app.delicare.dtos.RecipeDTO;
import com.app.delicare.entitys.Recipe;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.RecipeMapper;
import com.app.delicare.repositories.RecipeRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.RecipeResponse;
import com.app.delicare.service.implement.IRecipeService;
import com.app.delicare.specification.RecipeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RecipeService implements IRecipeService {
    private final RecipeMapper foodRevipeMapper;
    private final RecipeRepository foodRecipeRepository;
    private final UserRepository userRepository;

    @Override
    public RecipeResponse createFoodRecipe(RecipeDTO foodRecepeDTO) {
        try{
            Recipe foodRecipe = foodRevipeMapper.mapEntityToModel(foodRecepeDTO);
            foodRecipeRepository.save(foodRecipe);
            foodRecipeRepository.flush();
            return foodRevipeMapper.mapResponseToEntity(foodRecipe);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RecipeResponse> getAllFoodRecipen() {
        Specification<Recipe> specification = Specification.where(RecipeSpecification.isNotDeleted());
        return foodRecipeRepository.findAll(specification).stream().map(foodRecipe -> {
                    return  foodRevipeMapper.mapResponseToEntity(foodRecipe);
                })
                .toList();
    }

    @Override
    public Page<RecipeResponse> getListFoodRecipe(PageRequest pageRequest) {
        Specification<Recipe> specification = Specification.where(RecipeSpecification.isNotDeleted());
        return foodRecipeRepository.findAll(specification, pageRequest).map(foodRecipe -> {
            return foodRevipeMapper.mapResponseToEntity(foodRecipe);
        });
    }

    @Override
    public RecipeResponse updateFoodRecipe(Long id, RecipeDTO foodRecepeDTO) {
        User userModified = userRepository.getReferenceById(foodRecepeDTO.getModifiedById());
        Recipe foodIngredient = foodRevipeMapper.mapEntityToModel(foodRecepeDTO);
        foodIngredient.setId(id);
        foodIngredient.setModifiedByUser(userModified);
        foodRecipeRepository.save(foodIngredient);
        return foodRevipeMapper.mapResponseToEntity(foodIngredient);
    }

    @Override
    public RecipeResponse getFoodRecipeById(Long id) {
        Recipe foodRecipe = foodRecipeRepository.getReferenceById(id);
        return foodRevipeMapper.mapResponseToEntity(foodRecipe);
    }

    @Override
    public void deleteFoodRecipe(Long id) {

    }
}
