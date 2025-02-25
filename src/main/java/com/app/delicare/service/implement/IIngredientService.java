package com.app.delicare.service.implement;

import com.app.delicare.dtos.ingredient.IngredientDTO;
import com.app.delicare.responses.IngredientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IIngredientService {
    IngredientResponse createGFoodIngredien(IngredientDTO foodIngredientDTO);
    List<IngredientResponse> getAllFoodIngredien();
    Page<IngredientResponse> getListFoodIngredien(PageRequest pageRequest);
    IngredientResponse updateFoodIngredien(Long id, IngredientDTO foodIngredientDTO);
    IngredientResponse getFoodIngredienById(Long id);
    void deleteFoodIngredien(Long id);
}
