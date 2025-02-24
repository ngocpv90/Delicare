package com.app.delicare.mappers;

import com.app.delicare.dtos.ShippingDTO;
import com.app.delicare.entitys.Shipping;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.ShippingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShippingMapper extends BaseMapper {
    private final UserRepository userRepository;

    public Shipping mapEntityToModel(ShippingDTO shippingDTO){
        Shipping shipping = Shipping.builder()
                .shippingCode(shippingDTO.getShippingCode())
                .shippingName(shippingDTO.getShippingName())
                .build();
        shipping.setStatus(shippingDTO.getStatus());
        return shipping;
    }

    public ShippingResponse mapResponseToEntity(Shipping shipping){
        ShippingResponse shippingResponse = ShippingResponse.builder()
                .id(shipping.getId())
                .shippingCode(shipping.getShippingCode())
                .shippingName(shipping.getShippingName())
                .build();

        Optional.ofNullable(shipping)
                .map(Shipping::getCreatedByUser)
                .ifPresent(user -> {
                    shippingResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(shipping.getCreatedByUser()));
                });
        Optional.ofNullable(shipping)
                .map(Shipping::getModifiedByUser)
                .ifPresent(user -> {
                    shippingResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(shipping.getModifiedByUser()));
                });
        shippingResponse.setDeleted(shipping.getDeleted());
        shippingResponse.setDescription(shipping.getDescription());
        shippingResponse.setCreatedAt(shipping.getCreatedAt());
        shippingResponse.setCreatedAt(shipping.getCreatedAt());
        shippingResponse.setModifiedAt(shipping.getModifiedAt());
        shippingResponse.setStatus(shipping.getStatus());
        return shippingResponse;
    }
}
