package com.app.delicare.repositories;

import com.app.delicare.entitys.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>,
        JpaSpecificationExecutor<Ingredient> {
}
