package com.app.delicare.dtos.order;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO extends BaseDTO {
    @JsonProperty("orderId")
    private Long orderId;
    @JsonProperty("menuId")
    private Long menuId;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("price")
    private Long price;
    @JsonProperty("amountTotal")
    private Long amountTotal;
}
