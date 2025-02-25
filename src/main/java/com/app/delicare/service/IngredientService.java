package com.app.delicare.service;

import com.app.delicare.dtos.ingredient.IngredientDTO;
import com.app.delicare.entitys.ingredient.Ingredient;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.IngredientMapper;
import com.app.delicare.repositories.IngredientRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.IngredientResponse;
import com.app.delicare.service.implement.IIngredientService;
import com.app.delicare.specification.IngredientSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IngredientService implements IIngredientService {
    private final UserRepository userRepository;
    private final IngredientRepository foodIngredientRepository;
    private final IngredientMapper foodIngredientMapper;

    @Override
    public IngredientResponse createGFoodIngredien(IngredientDTO foodIngredientDTO) {
        try{
            Ingredient foodIngredient = foodIngredientMapper.mapEntityToModel(foodIngredientDTO);
            foodIngredientRepository.save(foodIngredient);
            foodIngredientRepository.flush();

            return foodIngredientMapper.mapResponseToEntity(foodIngredient);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<IngredientResponse> getAllFoodIngredien() {
        Specification<Ingredient> specification = Specification.where(IngredientSpecification.isNotDeleted());
        return foodIngredientRepository.findAll(specification).stream().map(foodIngredient -> {
                    IngredientResponse userResponse = foodIngredientMapper.mapResponseToEntity(foodIngredient);
                    return userResponse;
                })
                .toList();
    }

    @Override
    public Page<IngredientResponse> getListFoodIngredien(PageRequest pageRequest) {
        Specification<Ingredient> specification = Specification.where(IngredientSpecification.isNotDeleted());
        return foodIngredientRepository.findAll(specification, pageRequest).map(foodIngredient -> {
            return foodIngredientMapper.mapResponseToEntity(foodIngredient);
        });
    }

    @Override
    public IngredientResponse updateFoodIngredien(Long id, IngredientDTO foodIngredientDTO) {
        User userModified = userRepository.getReferenceById(foodIngredientDTO.getModifiedById());
        Ingredient foodIngredient = foodIngredientMapper.mapEntityToModel(foodIngredientDTO);
        foodIngredient.setId(id);
        foodIngredient.setModifiedByUser(userModified);
        foodIngredientRepository.save(foodIngredient);
        return foodIngredientMapper.mapResponseToEntity(foodIngredient);
    }

    @Override
    public IngredientResponse getFoodIngredienById(Long id) {
        return null;
    }

    @Override
    public void deleteFoodIngredien(Long id) {

    }
}
