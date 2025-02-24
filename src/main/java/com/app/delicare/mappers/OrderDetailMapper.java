package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.OrderDetailDTO;
import com.app.delicare.entitys.*;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.*;
import com.app.delicare.responses.OrderDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDetailMapper extends BaseMapper {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    private final OrderMapper orderMapper;
    private final MenuMapper menuMapper;

    public OrderDetail mapEntityToModel(OrderDetailDTO orderDetailDTO){
        OrderDetail orderDetail = OrderDetail.builder()
                .amountTotal(orderDetailDTO.getAmountTotal())
                .quantity(orderDetailDTO.getQuantity())
                .price(orderDetailDTO.getPrice())
                .build();

        if(!CommonUtils.isNullOrEmpty(orderDetailDTO.getOrderId())){
            Order order = orderRepository.getReferenceById(orderDetailDTO.getOrderId());
            orderDetail.setOrder(order);
        }
        if(!CommonUtils.isNullOrEmpty(orderDetailDTO.getMenuId())){
            Menu menu = menuRepository.getReferenceById(orderDetailDTO.getMenuId());
            orderDetail.setMenu(menu);
        }
        orderDetail.setStatus(orderDetailDTO.getStatus());
        return orderDetail;
    }

    public OrderDetailResponse mapResponseToEntity(OrderDetail orderDetail){
        OrderDetailResponse orderResponse = OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .amountTotal(orderDetail.getAmountTotal())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .build();

        Optional.ofNullable(orderDetail)
                .map(OrderDetail::getOrder)
                .ifPresent(user -> {
                    orderResponse.setOrderResponse(orderMapper.mapResponseToEntity(orderDetail.getOrder()));
                });
        Optional.ofNullable(orderDetail)
                .map(OrderDetail::getMenu)
                .ifPresent(user -> {
                    orderResponse.setMenuResponse(menuMapper.mapResponseToEntity(orderDetail.getMenu()));
                });

        Optional.ofNullable(orderDetail)
                .map(OrderDetail::getCreatedByUser)
                .ifPresent(user -> {
                    orderResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(orderDetail.getCreatedByUser()));
                });
        Optional.ofNullable(orderDetail)
                .map(OrderDetail::getModifiedByUser)
                .ifPresent(user -> {
                    orderResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(orderDetail.getModifiedByUser()));
                });
        orderResponse.setDeleted(orderDetail.getDeleted());
        orderResponse.setDescription(orderDetail.getDescription());
        orderResponse.setCreatedAt(orderDetail.getCreatedAt());
        orderResponse.setCreatedAt(orderDetail.getCreatedAt());
        orderResponse.setModifiedAt(orderDetail.getModifiedAt());
        orderResponse.setStatus(orderDetail.getStatus());
        return orderResponse;
    }
}
