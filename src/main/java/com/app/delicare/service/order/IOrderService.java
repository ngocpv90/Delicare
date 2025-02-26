package com.app.delicare.service.order;

import com.app.delicare.dtos.order.OrderDTO;
import com.app.delicare.dtos.order.OrderDetailsDTO;
import com.app.delicare.responses.order.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO);
    OrderResponse createOrder(OrderDTO orderDTO, OrderDetailsDTO orderDetailsDTO);
    List<OrderResponse> getAllOrder(OrderDTO orderDTO);
    Page<OrderResponse> getListOrder(PageRequest pageRequest, OrderDTO orderDTO);
    OrderResponse updateOrder(Long id, OrderDTO orderDTO);
    OrderResponse getOrderById(Long id);
    void deleteOrder(Long id);
}
