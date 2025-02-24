package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponse extends BaseResponse {
    private Long id;
    private String shippingCode;
    private String shippingName;
}
