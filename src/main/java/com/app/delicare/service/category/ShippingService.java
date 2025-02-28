package com.app.delicare.service.category;

import com.app.delicare.dtos.category.ShippingDTO;
import com.app.delicare.entitys.category.Shipping;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.category.ShippingMapper;
import com.app.delicare.repositories.category.ShippingRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.category.ShippingResponse;
import com.app.delicare.specification.category.ShippingSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingService implements IShippingService {
    private final ShippingRepository shippingRepository;
    private final ShippingMapper shippingMapper;
    private final UserRepository userRepository;

    @Override
    public ShippingResponse createShipping(ShippingDTO shippingDTO) {
        try{
            Shipping shipping = shippingMapper.mapEntityToModel(shippingDTO);
            shippingRepository.save(shipping);
            shippingRepository.flush();
            return shippingMapper.mapResponseToEntity(shipping);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ShippingResponse> getAllShipping() {
        Specification<Shipping> specification = Specification.where(ShippingSpecification.isNotDeleted());
        return shippingRepository.findAll(specification).stream().map(shipping -> {
                    return  shippingMapper.mapResponseToEntity(shipping);
                })
                .toList();
    }

    @Override
    public Page<ShippingResponse> getListShipping(PageRequest pageRequest) {
        Specification<Shipping> specification = Specification.where(ShippingSpecification.isNotDeleted());
        return shippingRepository.findAll(specification, pageRequest).map(shipping -> {
            return shippingMapper.mapResponseToEntity(shipping);
        });
    }

    @Override
    public ShippingResponse updateShipping(Long id, ShippingDTO shippingDTO) {
        User userModified = userRepository.getReferenceById(shippingDTO.getModifiedById());
        Shipping shipping = shippingMapper.mapEntityToModel(shippingDTO);
        shipping.setId(id);
        shipping.setModifiedByUser(userModified);
        shippingRepository.save(shipping);
        return shippingMapper.mapResponseToEntity(shipping);
    }

    @Override
    public ShippingResponse getShippingById(Long id) {
        return null;
    }

    @Override
    public void deleteShipping(Long id) {

    }
}
