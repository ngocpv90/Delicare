package com.app.delicare.responses.order;

import com.app.delicare.responses.base.BaseResponse;
import com.app.delicare.responses.menu.MenuResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse extends BaseResponse {
    private Long id;
    private OrderResponse orderResponse;
    private MenuResponse menuResponse;
    private Long quantity;
    private Long price;
    private Long amountTotal;
}
