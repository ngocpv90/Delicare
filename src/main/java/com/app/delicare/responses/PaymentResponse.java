package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse extends BaseResponse {
    private Long id;
    private String paymentCode;
    private String paymentName;
}
