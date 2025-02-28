package com.app.delicare.service.recipe;
import com.app.delicare.dtos.recipe.RecipeIngredientDTO;
import com.app.delicare.entitys.recipe.RecipeIngredient;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.recipe.RecipeIngredientMapper;
import com.app.delicare.repositories.ingredient.RecipeIngredientRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.recipe.RecipeIngredientResponse;
import com.app.delicare.specification.recipe.RecipeIngredientSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RecipeIngredientService implements IRecipeIngredientService {
    private final UserRepository userRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;

    @Override
    public RecipeIngredientResponse createRecipe(RecipeIngredientDTO recipeIngredientDTO) {
        try{
            RecipeIngredient recipeIngredient = recipeIngredientMapper.mapEntityToModel(recipeIngredientDTO);
            recipeIngredientRepository.save(recipeIngredient);
            recipeIngredientRepository.flush();
            return recipeIngredientMapper.mapResponseToEntity(recipeIngredient);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RecipeIngredientResponse> getAllRecipe() {
        Specification<RecipeIngredient> specification = Specification.where(RecipeIngredientSpecification.isNotDeleted());
        return recipeIngredientRepository.findAll(specification).stream().map(recipeIngredient -> {
                    return  recipeIngredientMapper.mapResponseToEntity(recipeIngredient);
                })
                .toList();
    }

    @Override
    public Page<RecipeIngredientResponse> getListRecipe(PageRequest pageRequest) {
        Specification<RecipeIngredient> specification = Specification.where(RecipeIngredientSpecification.isNotDeleted());
        return recipeIngredientRepository.findAll(specification, pageRequest).map(recipeIngredient -> {
            return recipeIngredientMapper.mapResponseToEntity(recipeIngredient);
        });
    }

    @Override
    public RecipeIngredientResponse updateRecipe(Long id, RecipeIngredientDTO recipeIngredientDTO) {
        User userModified = userRepository.getReferenceById(recipeIngredientDTO.getModifiedById());
        RecipeIngredient recipeIngredient = recipeIngredientMapper.mapEntityToModel(recipeIngredientDTO);
        recipeIngredient.setId(id);
        recipeIngredient.setModifiedByUser(userModified);
        recipeIngredientRepository.save(recipeIngredient);
        return recipeIngredientMapper.mapResponseToEntity(recipeIngredient);
    }

    @Override
    public RecipeIngredientResponse getRecipeById(Long id) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.getReferenceById(id);
        return recipeIngredientMapper.mapResponseToEntity(recipeIngredient);
    }

    @Override
    public void deleteRecipe(Long id) {

    }
}
