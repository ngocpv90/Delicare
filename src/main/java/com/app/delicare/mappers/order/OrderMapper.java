package com.app.delicare.mappers.order;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.order.OrderDTO;
import com.app.delicare.entitys.category.Category;
import com.app.delicare.entitys.category.Payment;
import com.app.delicare.entitys.category.Shipping;
import com.app.delicare.entitys.category.Stage;
import com.app.delicare.entitys.order.Order;
import com.app.delicare.entitys.users.UserAddress;
import com.app.delicare.mappers.category.PaymentMapper;
import com.app.delicare.mappers.category.ShippingMapper;
import com.app.delicare.mappers.category.StageMapper;
import com.app.delicare.mappers.user.UserAddressMapper;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.mappers.category.CategoryMappter;
import com.app.delicare.repositories.category.CategoryRepository;
import com.app.delicare.repositories.category.PaymentRepository;
import com.app.delicare.repositories.category.ShippingRepository;
import com.app.delicare.repositories.category.StageRepository;
import com.app.delicare.repositories.user.UserAddressRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final ShippingRepository shippingRepository;
    private final CategoryRepository categoryRepository;
    private final UserAddressRepository userAddressRepository;
    private final StageRepository stageRepository;
    private final PaymentMapper paymentMapper;
    private final ShippingMapper shippingMapper;
    private final CategoryMappter categoryMappter;
    private final UserAddressMapper userAddressMapper;
    private final StageMapper stageMapper;

    public Order mapEntityToModel(OrderDTO orderDTO){
        Order order = Order.builder()
                .amountTotal(orderDTO.getAmountTotal())
                .build();
        if(!CommonUtils.isNullOrEmpty(orderDTO.getPaymentId())){
            Payment payment = paymentRepository.getReferenceById(orderDTO.getPaymentId());
            order.setPayment(payment);
        }
        if(!CommonUtils.isNullOrEmpty(orderDTO.getShippingId())){
            Shipping shipping = shippingRepository.getReferenceById(orderDTO.getShippingId());
            order.setShipping(shipping);
        }
        if(!CommonUtils.isNullOrEmpty(orderDTO.getPaymentStatusId())){
            Category category = categoryRepository.getReferenceById(orderDTO.getPaymentStatusId());
            order.setPaymentStatus(category);
        }
        if(!CommonUtils.isNullOrEmpty(orderDTO.getShippingStatusId())){
            Category category = categoryRepository.getReferenceById(orderDTO.getShippingStatusId());
            order.setShippingStatus(category);
        }
        if(!CommonUtils.isNullOrEmpty(orderDTO.getUserAddressId())){
            UserAddress userAddress = userAddressRepository.getReferenceById(orderDTO.getUserAddressId());
            order.setUserAddress(userAddress);
        }
        if(!CommonUtils.isNullOrEmpty(orderDTO.getStageId())){
            Stage stage = stageRepository.getReferenceById(orderDTO.getStageId());
            order.setStage(stage);
        }
        order.setStatus(orderDTO.getStatus());
        return order;
    }

    public OrderResponse mapResponseToEntity(Order order){
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .amountTotal(order.getAmountTotal())
                .build();
        Optional.ofNullable(order)
                .map(Order::getPayment)
                .ifPresent(user -> {
                    orderResponse.setPaymentResponse(paymentMapper.mapResponseToEntity(order.getPayment()));
                });
        Optional.ofNullable(order)
                .map(Order::getShipping)
                .ifPresent(user -> {
                    orderResponse.setShippingResponse(shippingMapper.mapResponseToEntity(order.getShipping()));
                });
        Optional.ofNullable(order)
                .map(Order::getPaymentStatus)
                .ifPresent(category -> {
                    orderResponse.setPaymentStatus(categoryMappter.mapResponseToEntity(order.getPaymentStatus()));
                });
        Optional.ofNullable(order)
                .map(Order::getShippingStatus)
                .ifPresent(category -> {
                    orderResponse.setShippingStatus(categoryMappter.mapResponseToEntity(order.getShippingStatus()));
                });
        Optional.ofNullable(order)
                .map(Order::getUserAddress)
                .ifPresent(userAddress -> {
                    orderResponse.setUserAddressResponse(userAddressMapper.mapResponseToEntity(order.getUserAddress()));
                });
        Optional.ofNullable(order)
                .map(Order::getStage)
                .ifPresent(stage -> {
                    orderResponse.setStageResponse(stageMapper.mapResponseToEntity(order.getStage()));
                });
        Optional.ofNullable(order)
                .map(Order::getCreatedByUser)
                .ifPresent(user -> {
                    orderResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(order.getCreatedByUser()));
                });
        Optional.ofNullable(order)
                .map(Order::getModifiedByUser)
                .ifPresent(user -> {
                    orderResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(order.getModifiedByUser()));
                });
        orderResponse.setDeleted(order.getDeleted());
        orderResponse.setDescription(order.getDescription());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setModifiedAt(order.getModifiedAt());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }
}
