package com.app.delicare.dtos.product;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImgDTO extends BaseDTO{
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("iconPath")
    private String iconPath;
    @JsonProperty("sort")
    private Long sort;
}
