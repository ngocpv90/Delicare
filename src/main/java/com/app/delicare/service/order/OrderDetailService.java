package com.app.delicare.service.order;

import com.app.delicare.dtos.order.OrderDetailDTO;
import com.app.delicare.entitys.order.OrderDetail;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.order.OrderDetailMapper;
import com.app.delicare.repositories.order.OrderDetailRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.order.OrderDetailResponse;
import com.app.delicare.specification.order.OrderDetailSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final UserRepository userRepository;

    @Override
    public Long saveAll(List<OrderDetailDTO> orderDetailDTOS) {
        try{
            List<OrderDetail> orderDetails = orderDetailMapper.mapEntityToModel(orderDetailDTOS);
            orderDetailRepository.saveAll(orderDetails);
            orderDetailRepository.flush();
            return orderDetails.stream().count();
        } catch (Exception e){
            return 0L;
        }
    }

    @Override
    public OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO) {
        try{
            OrderDetail orderDetail = orderDetailMapper.mapEntityToModel(orderDetailDTO);
            orderDetailRepository.save(orderDetail);
            orderDetailRepository.flush();
            return orderDetailMapper.mapResponseToEntity(orderDetail);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<OrderDetailResponse> getAllOrderDetail() {
        Specification<OrderDetail> specification = Specification.where(OrderDetailSpecification.isNotDeleted());
        return orderDetailRepository.findAll(specification).stream().map(orderDetail -> {
                    return  orderDetailMapper.mapResponseToEntity(orderDetail);
                })
                .toList();
    }

    @Override
    public Page<OrderDetailResponse> getListOrderDetail(PageRequest pageRequest) {
        Specification<OrderDetail> specification = Specification.where(OrderDetailSpecification.isNotDeleted());
        return orderDetailRepository.findAll(specification, pageRequest).map(orderDetail -> {
            return orderDetailMapper.mapResponseToEntity(orderDetail);
        });
    }

    @Override
    public OrderDetailResponse updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) {
        User userModified = userRepository.getReferenceById(orderDetailDTO.getModifiedById());
        OrderDetail orderDetail = orderDetailMapper.mapEntityToModel(orderDetailDTO);
        orderDetail.setId(id);
        orderDetail.setModifiedByUser(userModified);
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.mapResponseToEntity(orderDetail);
    }

    @Override
    public OrderDetailResponse getOrderDetailById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.getReferenceById(id);
        return orderDetailMapper.mapResponseToEntity(orderDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {

    }

    @Override
    public void deleteOrderDetailByOrderId(Long id) {

    }
}
