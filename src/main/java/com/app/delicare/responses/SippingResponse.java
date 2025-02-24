package com.app.delicare.responses;

import com.app.delicare.responses.base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SippingResponse extends BaseResponse {
    private Long id;
    private String shippingCode;
    private String shippingName;
}
