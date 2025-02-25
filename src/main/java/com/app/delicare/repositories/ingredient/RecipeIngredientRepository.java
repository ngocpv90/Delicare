package com.app.delicare.repositories.ingredient;

import com.app.delicare.entitys.recipe.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long>,
        JpaSpecificationExecutor<RecipeIngredient> {
}

