package com.app.delicare.service;

import com.app.delicare.dtos.ShippingDTO;
import com.app.delicare.entitys.Recipe;
import com.app.delicare.entitys.Shipping;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.ShippingMapper;
import com.app.delicare.repositories.ShippingRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.ShippingResponse;
import com.app.delicare.service.implement.IShippingService;
import com.app.delicare.specification.RecipeSpecification;
import com.app.delicare.specification.ShippingSpecification;
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
