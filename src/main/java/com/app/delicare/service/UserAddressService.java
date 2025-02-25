package com.app.delicare.service;
import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.dtos.user.UserAddressDTO;
import com.app.delicare.entitys.user.User;
import com.app.delicare.entitys.user.UserAddress;
import com.app.delicare.mappers.UserAddressMapper;
import com.app.delicare.repositories.user.UserAddressRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.user.UserAddressResponse;
import com.app.delicare.service.implement.IUserAddressService;
import com.app.delicare.specification.UserAddressSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService implements IUserAddressService {
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserAddressMapper userAddressMapper;

    @Override
    public UserAddressResponse createUserAddress(UserAddressDTO userAddressDTO) {
        try{
            UserAddress userAddress = userAddressMapper.mapModelToEntity(userAddressDTO);
            userAddressRepository.save(userAddress);
            userAddressRepository.flush();
            return userAddressMapper.mapResponseToEntity(userAddress);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserAddressResponse> getAllUserAddress() {
        Specification<UserAddress> specification = Specification.where(UserAddressSpecification.isNotDeleted());
        return userAddressRepository.findAll(specification).stream().map(userContact -> {
                    return userAddressMapper.mapResponseToEntity(userContact);
                })
                .toList();
    }

    @Override
    public Page<UserAddressResponse> getPageUserAddress(PageRequest pageRequest) {
        Specification<UserAddress> specification = Specification.where(UserAddressSpecification.isNotDeleted());
        return userAddressRepository.findAll(specification, pageRequest).map(userContact -> {
            return userAddressMapper.mapResponseToEntity(userContact);
        });
    }

    @Override
    public UserAddressResponse getUserAddressById(Long id) {
        UserAddress userAddress = userAddressRepository.getReferenceById(id);
        return userAddressMapper.mapResponseToEntity(userAddress);
    }

    @Override
    public List<UserAddressResponse> getUserAddressByUserId(Long userId) {
        Specification<UserAddress> specification = Specification.where(UserAddressSpecification.isNotDeleted())
                .and(UserAddressSpecification.hasUserId(userId, ESpecification.EQUAL.getValue()));
        return userAddressRepository.findAll(specification).stream().map(userAddress -> {
            return userAddressMapper.mapResponseToEntity(userAddress);
        }).toList();
    }

    @Override
    public UserAddressResponse updateUserAddress(Long id, UserAddressDTO userAddressDTO) {
        User userModified = userRepository.getReferenceById(userAddressDTO.getModifiedById());
        UserAddress userUpdate = userAddressMapper.mapModelToEntity(userAddressDTO);
        userUpdate.setId(id);
        userUpdate.setModifiedByUser(userModified);
        userAddressRepository.save(userUpdate);
        return userAddressMapper.mapResponseToEntity(userUpdate);
    }

    @Override
    public void deleteUserAddressById(Long id) {
    }
}
