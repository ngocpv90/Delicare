package com.app.delicare.responses.order;

import com.app.delicare.responses.category.PaymentResponse;
import com.app.delicare.responses.category.ShippingResponse;
import com.app.delicare.responses.category.StageResponse;
import com.app.delicare.responses.base.BaseResponse;
import com.app.delicare.responses.category.CategoryResponse;
import com.app.delicare.responses.user.UserAddressResponse;
import com.app.delicare.responses.user.UserResponse;
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
