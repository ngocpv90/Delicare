package com.app.delicare.dtos.order;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Long orderId;
    private List<OrderDetailDTO> orderDetails;
}
