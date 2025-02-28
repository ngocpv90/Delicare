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
public class ProductImgResponse extends BaseResponse {
    private Long id;
    private ProductResponse productResponse;
    private String iconPath;
    private Long sort;
}
