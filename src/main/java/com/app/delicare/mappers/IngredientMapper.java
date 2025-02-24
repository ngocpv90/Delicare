package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.IngredientDTO;
import com.app.delicare.entitys.*;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.UnitRepository;
import com.app.delicare.responses.IngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IngredientMapper extends BaseMapper {
    private final UnitMapper unitMapper;
    private final UnitRepository unitRepository;

    public Ingredient mapEntityToModel(IngredientDTO foodIngredientDTO){
        Ingredient foodIngredient = Ingredient.builder()
                .ingredientCode(foodIngredientDTO.getIngredientCode())
                .ingredientName(foodIngredientDTO.getIngredientName())
                .build();
        if(!CommonUtils.isNullOrEmpty(foodIngredientDTO.getUnitId())){
            Units units = unitRepository.getReferenceById(foodIngredientDTO.getUnitId());
            foodIngredient.setUnits(units);
        }
        foodIngredient.setStatus(foodIngredientDTO.getStatus());
        return foodIngredient;
    }

    public IngredientResponse mapResponseToEntity(Ingredient foodIngredient){
        IngredientResponse foodIngredientResponse = IngredientResponse.builder()
                .id(foodIngredient.getId())
                .ingredientCode(foodIngredient.getIngredientCode())
                .ingredientName(foodIngredient.getIngredientName())
                .build();

        Optional.ofNullable(foodIngredient)
                .map(Ingredient::getUnits)
                .ifPresent(units -> {
                    foodIngredientResponse.setUnitResponse(unitMapper.mapResponseToEntity(foodIngredient.getUnits()));
                });
        Optional.ofNullable(foodIngredient)
                .map(Ingredient::getCreatedByUser)
                .ifPresent(user -> {
                    foodIngredientResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(foodIngredient.getCreatedByUser()));
                });
        Optional.ofNullable(foodIngredient)
                .map(Ingredient::getModifiedByUser)
                .ifPresent(user -> {
                    foodIngredientResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(foodIngredient.getModifiedByUser()));
                });
        foodIngredientResponse.setDeleted(foodIngredient.getDeleted());
        foodIngredientResponse.setDescription(foodIngredient.getDescription());
        foodIngredientResponse.setCreatedAt(foodIngredient.getCreatedAt());
        foodIngredientResponse.setCreatedAt(foodIngredient.getCreatedAt());
        foodIngredientResponse.setModifiedAt(foodIngredient.getModifiedAt());
        foodIngredientResponse.setStatus(foodIngredient.getStatus());
        return foodIngredientResponse;
    }
}
