package com.app.delicare.service;

import com.app.delicare.dtos.OrderDTO;
import com.app.delicare.entitys.Menu;
import com.app.delicare.entitys.Order;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.OrderMapper;
import com.app.delicare.repositories.OrderRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.OrderResponse;
import com.app.delicare.service.implement.IOrderService;
import com.app.delicare.specification.MenuSpecification;
import com.app.delicare.specification.OrderSpecification;
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
    public List<OrderResponse> getAllOrder() {
        Specification<Order> specification = Specification.where(OrderSpecification.isNotDeleted());
        return orderRepository.findAll(specification).stream().map(order -> {
                    return  orderMapper.mapResponseToEntity(order);
                })
                .toList();
    }

    @Override
    public Page<OrderResponse> getListOrder(PageRequest pageRequest) {
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
