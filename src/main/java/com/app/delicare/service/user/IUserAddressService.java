package com.app.delicare.service.user;

import com.app.delicare.dtos.user.UserAddressDTO;
import com.app.delicare.responses.user.UserAddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserAddressService{
    UserAddressResponse createUserAddress(UserAddressDTO userAddressDTO);
    List<UserAddressResponse> getAllUserAddress();
    Page<UserAddressResponse> getPageUserAddress(PageRequest pageRequest);
    UserAddressResponse getUserAddressById(Long id);
    List<UserAddressResponse> getUserAddressByUserId(Long userId);
    UserAddressResponse updateUserAddress(Long id, UserAddressDTO userAddressDTO);
    void deleteUserAddressById(Long id);
}
