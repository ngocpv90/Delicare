package com.app.delicare.mappers;
import com.app.delicare.dtos.GroupDTO;
import com.app.delicare.dtos.ProvinceDTO;
import com.app.delicare.entitys.Department;
import com.app.delicare.entitys.Group;
import com.app.delicare.entitys.Province;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.GroupResponse;
import com.app.delicare.responses.ProvinceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProvinceMapper extends BaseMapper {
    public Province mapEntityToModel(ProvinceDTO provinceDTO){
        Province province = Province.builder()
                .provinceCode(provinceDTO.getProvinceCode())
                .provinceCode(provinceDTO.getProvinceName())
                .build();
        province.setStatus(provinceDTO.getStatus());
        return province;
    }
    public Province mapEntityToModel(Long id, ProvinceDTO provinceDTO){
        Province province = mapEntityToModel(provinceDTO);
        province.setId(id);
        return province;
    }
    public ProvinceResponse mapResponseToEntity(Province province){
        ProvinceResponse provinceResponse = ProvinceResponse.builder()
                .id(province.getId())
                .provinceCode(province.getProvinceCode())
                .provinceName(province.getProvinceName())
                .build();
        Optional.ofNullable(province)
                .map(Province::getCreatedByUser)
                .ifPresent(user1 -> {
                    provinceResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(province.getCreatedByUser()));
                });
        Optional.ofNullable(province)
                .map(Province::getCreatedByUser)
                .ifPresent(user1 -> {
                    provinceResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(province.getModifiedByUser()));
                });
        provinceResponse.setDeleted(province.getDeleted());
        provinceResponse.setDescription(province.getDescription());
        provinceResponse.setCreatedAt(province.getCreatedAt());
        provinceResponse.setCreatedAt(province.getCreatedAt());
        provinceResponse.setModifiedAt(province.getModifiedAt());
        provinceResponse.setStatus(province.getStatus());
        return provinceResponse;
    }
    public List<ProvinceResponse> mapResponseToEntity(List<Province> provinces){
        return provinces.stream().map(province -> {
            return mapResponseToEntity(province);
        }).toList();
    }
}
