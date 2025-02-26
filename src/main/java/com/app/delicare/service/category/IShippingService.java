package com.app.delicare.service.category;

import com.app.delicare.dtos.category.ShippingDTO;
import com.app.delicare.responses.category.ShippingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IShippingService {
    ShippingResponse createShipping(ShippingDTO shippingDTO);
    List<ShippingResponse> getAllShipping();
    Page<ShippingResponse> getListShipping(PageRequest pageRequest);
    ShippingResponse updateShipping(Long id, ShippingDTO shippingDTO);
    ShippingResponse getShippingById(Long id);
    void deleteShipping(Long id);
}
