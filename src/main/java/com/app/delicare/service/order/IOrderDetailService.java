package com.app.delicare.service.order;

import com.app.delicare.dtos.order.OrderDetailDTO;
import com.app.delicare.responses.order.OrderDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IOrderDetailService {
    Long saveAll(List<OrderDetailDTO> orderDetailDTOS);
    OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO);
    List<OrderDetailResponse> getAllOrderDetail();
    Page<OrderDetailResponse> getListOrderDetail(PageRequest pageRequest);
    OrderDetailResponse updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO);
    OrderDetailResponse getOrderDetailById(Long id);
    void deleteOrderDetail(Long id);
    void deleteOrderDetailByOrderId(Long id);
}
