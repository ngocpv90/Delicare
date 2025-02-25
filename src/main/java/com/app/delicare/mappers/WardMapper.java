package com.app.delicare.mappers;
import com.app.delicare.dtos.address.WardDTO;
import com.app.delicare.entitys.address.District;
import com.app.delicare.entitys.address.Ward;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.address.WardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WardMapper extends BaseMapper {
    private final DistrictMapper districtMapper;

    public Ward mapEntityToModel(WardDTO wardDTO){
        Ward ward = Ward.builder()
                .wardCode(wardDTO.getWardCode())
                .wardName(wardDTO.getWardName())
                .build();
        ward.setStatus(wardDTO.getStatus());
        return ward;
    }
    public Ward mapEntityToModel(WardDTO wardDTO, District district){
        Ward ward = mapEntityToModel(wardDTO);
        ward.setDistrict(district);
        return ward;
    }
    public Ward mapEntityToModel(Long id, WardDTO wardDTO, District district){
        Ward ward = mapEntityToModel(wardDTO, district);
        ward.setId(id);
        return ward;
    }
    public WardResponse mapResponseToEntity(Ward ward){
        WardResponse wardResponse = WardResponse.builder()
                .id(ward.getId())
                .wardCode(ward.getWardCode())
                .wardName(ward.getWardName())
                .build();
        Optional.ofNullable(ward)
                .map(Ward::getDistrict)
                .ifPresent(district -> {
                    wardResponse.setDistrictResponse(districtMapper.mapResponseToEntity(ward.getDistrict()));
                });
        Optional.ofNullable(ward)
                .map(Ward::getCreatedByUser)
                .ifPresent(user1 -> {
                    wardResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(ward.getCreatedByUser()));
                });
        Optional.ofNullable(ward)
                .map(Ward::getCreatedByUser)
                .ifPresent(user1 -> {
                    wardResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(ward.getModifiedByUser()));
                });
        wardResponse.setDeleted(ward.getDeleted());
        wardResponse.setDescription(ward.getDescription());
        wardResponse.setCreatedAt(ward.getCreatedAt());
        wardResponse.setCreatedAt(ward.getCreatedAt());
        wardResponse.setModifiedAt(ward.getModifiedAt());
        wardResponse.setStatus(ward.getStatus());
        return wardResponse;
    }
    public List<WardResponse> mapResponseToEntity(List<Ward> wards){
        return wards.stream().map(ward -> {
            return mapResponseToEntity(ward);
        }).toList();
    }
}
