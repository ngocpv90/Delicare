package com.app.delicare.mappers;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.UserAddressDTO;
import com.app.delicare.dtos.UserDTO;
import com.app.delicare.entitys.*;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.DistrictRepository;
import com.app.delicare.repositories.ProvinceRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.repositories.WardRepository;
import com.app.delicare.responses.UserAddressResponse;
import com.app.delicare.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAddressMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final ProvinceMapper provinceMapper;
    private final DistrictMapper districtMapper;
    private final WardMapper wardMapper;

    public UserAddress mapModelToEntity(UserAddressDTO userAddressDTO){
        UserAddress userMapper = UserAddress.builder()
                .mapId(userAddressDTO.getMapId())
                .longitude(userAddressDTO.getLongitude())
                .latitude(userAddressDTO.getLatitude())
                .build();
        if(!CommonUtils.isNullOrEmpty(userAddressDTO.getProvinceId())){
            Province province = provinceRepository.getReferenceById(userAddressDTO.getProvinceId());
            userMapper.setProvince(province);
        }
        if(!CommonUtils.isNullOrEmpty(userAddressDTO.getDistrictId())){
            District district= districtRepository.getReferenceById(userAddressDTO.getDistrictId());
            userMapper.setDistrict(district);
        }
        if(!CommonUtils.isNullOrEmpty(userAddressDTO.getWardId())){
            Ward ward = wardRepository.getReferenceById(userAddressDTO.getWardId());
            userMapper.setWard(ward);
        }
        if(!CommonUtils.isNullOrEmpty(userAddressDTO.getCreatedById())){
            User user = userRepository.getReferenceById(userAddressDTO.getCreatedById());
            userMapper.setCreatedByUser(user);
            userMapper.setModifiedByUser(user);
        }
        userMapper.setDeleted(userAddressDTO.getDeleted());
        userMapper.setDescription(userAddressDTO.getDescription());
        userMapper.setStatus(userAddressDTO.getStatus());
        return userMapper;
    }

    public UserAddressResponse mapResponseToEntity(UserAddress userAddress){
        UserAddressResponse userResponse = UserAddressResponse.builder()
                .id(userAddress.getId())
                .mapId(userAddress.getMapId())
                .longitude(userAddress.getLongitude())
                .latitude(userAddress.getLatitude())
                .build();
        Optional.ofNullable(userAddress)
                .map(UserAddress::getProvince)
                .ifPresent(province -> {
                    userResponse.setProvinceResponse(provinceMapper.mapResponseToEntity(userAddress.getProvince()));
                });
        Optional.ofNullable(userAddress)
                .map(UserAddress::getDistrict)
                .ifPresent(district -> {
                    userResponse.setDistrictResponse(districtMapper.mapResponseToEntity(userAddress.getDistrict()));
                });
        Optional.ofNullable(userAddress)
                .map(UserAddress::getWard)
                .ifPresent(ward -> {
                    userResponse.setWardResponse(wardMapper.mapResponseToEntity(userAddress.getWard()));
                });
        Optional.ofNullable(userAddress)
                .map(UserAddress::getCreatedByUser)
                .ifPresent(user -> {
                    userResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(userAddress.getCreatedByUser()));
                });
        Optional.ofNullable(userAddress)
                .map(UserAddress::getCreatedByUser)
                .ifPresent(user -> {
                    userResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(userAddress.getModifiedByUser()));
                });
        userResponse.setDeleted(userAddress.getDeleted());
        userResponse.setDescription(userAddress.getDescription());
        userResponse.setCreatedAt(userAddress.getCreatedAt());
        userResponse.setCreatedAt(userAddress.getCreatedAt());
        userResponse.setModifiedAt(userAddress.getModifiedAt());
        userResponse.setStatus(userAddress.getStatus());
        return  userResponse;
    }
}
