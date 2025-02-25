package com.app.delicare.dtos.category;
import com.app.delicare.dtos.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDTO extends BaseDTO{
    @NotEmpty(message = "code is not null")
    @JsonProperty("shippingCode")
    private String shippingCode;
    @NotEmpty(message = "name is not null")
    @JsonProperty("shippingName")
    private String shippingName;
}
