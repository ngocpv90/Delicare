package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class IngredientResponse extends BaseResponse {
//    private Long id;
    private String ingredientCode;
    private String ingredientName;
    private UnitResponse unitResponse;
}
