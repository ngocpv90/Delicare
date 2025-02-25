package com.app.delicare.entitys.recipe;
import com.app.delicare.entitys.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RECIPES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String recipeCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String recipeName;
    @Column(name = "TYPE")
    private String recipeType;
}
