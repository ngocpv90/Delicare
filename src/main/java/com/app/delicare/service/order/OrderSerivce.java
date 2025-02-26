package com.app.delicare.service.order;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.order.OrderDTO;
import com.app.delicare.dtos.order.OrderDetailsDTO;
import com.app.delicare.entitys.order.Order;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.order.OrderDetailMapper;
import com.app.delicare.mappers.order.OrderMapper;
import com.app.delicare.repositories.order.OrderDetailRepository;
import com.app.delicare.repositories.order.OrderRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.order.OrderResponse;
import com.app.delicare.specification.order.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSerivce implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) {
        try{
            Order order = orderMapper.mapEntityToModel(orderDTO);
            orderRepository.save(order);
            orderRepository.flush();
            return orderMapper.mapResponseToEntity(order);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO, OrderDetailsDTO orderDetailsDTO) {
        try{
            Order order = orderMapper.mapEntityToModel(orderDTO);
            orderRepository.save(order);
            orderRepository.flush();

            if(!CommonUtils.isNullOrEmpty(orderDetailsDTO.getOrderDetails())){
                orderDetailsDTO.getOrderDetails().forEach(orderDetailDTO -> {
                    orderDetailDTO.setOrderId(order.getId());
                });
                orderDetailRepository.deleteByOrderId(order.getId());
                orderDetailRepository.saveAll(orderDetailMapper.mapEntityToModel(orderDetailsDTO.getOrderDetails()));
            }
            return orderMapper.mapResponseToEntity(order);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<OrderResponse> getAllOrder(OrderDTO orderDTO) {
        Specification<Order> specification = Specification.where(OrderSpecification.isNotDeleted());
        return orderRepository.findAll(specification).stream().map(order -> {
                    return  orderMapper.mapResponseToEntity(order);
                })
                .toList();
    }

    @Override
    public Page<OrderResponse> getListOrder(PageRequest pageRequest, OrderDTO orderDTO) {
        Specification<Order> specification = Specification.where(OrderSpecification.isNotDeleted());
        return orderRepository.findAll(specification, pageRequest).map(order -> {
            return orderMapper.mapResponseToEntity(order);
        });
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderDTO orderDTO) {
        User userModified = userRepository.getReferenceById(orderDTO.getModifiedById());
        Order order = orderMapper.mapEntityToModel(orderDTO);
        order.setId(id);
        order.setModifiedByUser(userModified);
        orderRepository.save(order);
        return orderMapper.mapResponseToEntity(order);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.getReferenceById(id);
        return orderMapper.mapResponseToEntity(order);
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
