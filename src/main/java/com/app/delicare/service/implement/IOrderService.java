package com.app.delicare.service.implement;

import com.app.delicare.dtos.OrderDTO;
import com.app.delicare.responses.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO);
    List<OrderResponse> getAllOrder();
    Page<OrderResponse> getListOrder(PageRequest pageRequest);
    OrderResponse updateOrder(Long id, OrderDTO orderDTO);
    OrderResponse getOrderById(Long id);
    void deleteOrder(Long id);
}
