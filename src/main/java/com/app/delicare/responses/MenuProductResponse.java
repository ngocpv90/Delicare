package com.app.delicare.responses;

import com.app.delicare.entitys.Menu;
import com.app.delicare.entitys.Product;
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
