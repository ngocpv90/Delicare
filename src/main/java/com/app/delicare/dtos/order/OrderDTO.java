package com.app.delicare.dtos.order;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends BaseDTO{
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("stageId")
    private Long stageId;
    @JsonProperty("amountTotal")
    private Long amountTotal;
    @JsonProperty("paymentId")
    private Long paymentId;
    @JsonProperty("paymentStatusId")
    private Long paymentStatusId;
    @JsonProperty("shippingId")
    private Long shippingId;
    @JsonProperty("shippingStatusId")
    private Long shippingStatusId;
    @JsonProperty("userAddressId")
    private Long userAddressId;
}
