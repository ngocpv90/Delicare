package com.app.delicare.responses.menu;

import com.app.delicare.responses.product.ProductResponse;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuProductResponse extends BaseResponse {
    private Long id;
    private MenuResponse menuResponse;
    private ProductResponse productResponse;
}
