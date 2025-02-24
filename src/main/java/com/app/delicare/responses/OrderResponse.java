package com.app.delicare.responses;

import com.app.delicare.entitys.UserAddress;
import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse extends BaseResponse {
    private Long id;
    private String orderCode;
    private String orderName;
    private UserResponse userResponse;
    private StageResponse stageResponse;
    private Long amountTotal;
    private PaymentResponse paymentResponse;
    private CategoryResponse paymentStatus;
    private ShippingResponse shippingResponse;
    private CategoryResponse shippingStatus;
    private UserAddressResponse userAddressResponse;
}
