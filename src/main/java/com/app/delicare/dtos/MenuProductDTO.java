package com.app.delicare.dtos;

import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuProductDTO extends BaseDTO {
    @JsonProperty("menuId")
    private Long menuId;
    @JsonProperty("productId")
    private Long productId;
}
