package com.app.delicare.entitys.recipe;
import com.app.delicare.entitys.ingredient.Ingredient;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RECIPE_INGREDIENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeIngredient extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;
}
