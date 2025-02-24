package com.app.delicare.service.implement;

import com.app.delicare.dtos.OrderDTO;
import com.app.delicare.dtos.OrderDetailDTO;
import com.app.delicare.responses.OrderDetailResponse;
import com.app.delicare.responses.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO);
    List<OrderDetailResponse> getAllOrderDetail();
    Page<OrderDetailResponse> getListOrderDetail(PageRequest pageRequest);
    OrderDetailResponse updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO);
    OrderDetailResponse getOrderDetailById(Long id);
    void deleteOrderDetail(Long id);
}
