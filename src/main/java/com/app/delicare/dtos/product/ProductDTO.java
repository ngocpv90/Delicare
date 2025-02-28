package com.app.delicare.dtos.product;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("foodProductCode")
    private String productCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("foodProductName")
    private String productName;
    @JsonProperty("foodProductType")
    private String productType;
    @JsonProperty("iconPath")
    private String iconPath;
    @JsonProperty("productSort")
    private Long productSort;
}
