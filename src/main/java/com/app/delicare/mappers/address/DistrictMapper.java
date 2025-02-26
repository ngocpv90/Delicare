package com.app.delicare.mappers.address;
import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.address.DistrictDTO;
import com.app.delicare.entitys.address.District;
import com.app.delicare.entitys.address.Province;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.address.ProvinceRepository;
import com.app.delicare.responses.address.DistrictResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DistrictMapper extends BaseMapper {
    private final ProvinceMapper provinceMapper;
    private final ProvinceRepository provinceRepository;

    public District mapEntityToModel(DistrictDTO districtDTO){
        District district = District.builder()
                .districtCode(districtDTO.getDistrictCode())
                .districtName(districtDTO.getDistrictName())
                .build();
        if(!CommonUtils.isNullOrEmpty(districtDTO.getProvinceId())){
            Province province = provinceRepository.getReferenceById(districtDTO.getProvinceId());
            district.setProvince(province);
        }
        district.setStatus(districtDTO.getStatus());
        return district;
    }
    public District mapEntityToModel(DistrictDTO districtDTO, Province province){
        District district = mapEntityToModel(districtDTO);
        district.setProvince(province);
        return district;
    }
    public District mapEntityToModel(Long id, DistrictDTO districtDTO, Province province){
        District district = mapEntityToModel(districtDTO, province);
        district.setId(id);
        return district;
    }
    public DistrictResponse mapResponseToEntity(District district){
        DistrictResponse districtResponse = DistrictResponse.builder()
                .id(district.getId())
                .districtCode(district.getDistrictCode())
                .districtName(district.getDistrictName())
                .build();
        Optional.ofNullable(district)
                .map(District::getProvince)
                .ifPresent(province -> {
                    districtResponse.setProvinceResponse(provinceMapper.mapResponseToEntity(district.getProvince()));
                });
        Optional.ofNullable(district)
                .map(District::getCreatedByUser)
                .ifPresent(user1 -> {
                    districtResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(district.getCreatedByUser()));
                });
        Optional.ofNullable(district)
                .map(District::getCreatedByUser)
                .ifPresent(user1 -> {
                    districtResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(district.getModifiedByUser()));
                });

        districtResponse.setDeleted(district.getDeleted());
        districtResponse.setDescription(district.getDescription());
        districtResponse.setCreatedAt(district.getCreatedAt());
        districtResponse.setCreatedAt(district.getCreatedAt());
        districtResponse.setModifiedAt(district.getModifiedAt());
        districtResponse.setStatus(district.getStatus());
        return districtResponse;
    }
    public List<DistrictResponse> mapResponseToEntity(List<District> districts){
        return districts.stream().map(district -> {
            return mapResponseToEntity(district);
        }).toList();
    }
}
