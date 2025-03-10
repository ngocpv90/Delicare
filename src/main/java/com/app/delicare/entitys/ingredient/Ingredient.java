package com.app.delicare.entitys.ingredient;
import com.app.delicare.entitys.category.Units;
import com.app.delicare.entitys.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INGREDIENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient extends BaseEntity{
    @Column(name = "CODE", nullable = false, length = 50)
    private String ingredientCode;
    @Column(name = "NAME", nullable = false, length = 100)
    private String ingredientName;
    @ManyToOne
    @JoinColumn(name = "UNIT_ID")
    private Units units;
}
