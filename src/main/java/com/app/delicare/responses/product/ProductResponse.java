package com.app.delicare.responses.product;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductResponse extends BaseResponse {
    private Long id;
    private String productCode;
    private String productName;
    private String productType;
    private String iconPath;
    private Long productSort;
}
