package com.app.delicare.service;

import com.app.delicare.entitys.address.District;
import com.app.delicare.entitys.user.User;
import com.app.delicare.entitys.address.Ward;
import com.app.delicare.dtos.address.WardDTO;
import com.app.delicare.repositories.address.DistrictRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.repositories.address.WardRepository;
import com.app.delicare.responses.address.WardResponse;
import com.app.delicare.service.implement.IWardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WardService implements IWardService {
    private final WardRepository wardRespository;
    private final UserRepository userRepository;
    private final DistrictRepository districtRespository;
    @Override
    public WardResponse createWard(WardDTO wardDTO) {
        User user = userRepository.getReferenceById(wardDTO.getCreatedById());
        District district = districtRespository.getReferenceById(wardDTO.getDistrictId());
        Ward newWard = Ward.builder()
                .wardCode(wardDTO.getWardCode())
                .wardName(wardDTO.getWardName())
                .build();
        newWard.setDistrict(district);
        newWard.setCreatedByUser(user);
        wardRespository.save(newWard);
        return null;
    }

    @Override
    public List<Ward> getAllWard() {
        return wardRespository.findAll();
    }

    @Override
    public List<WardResponse> getWardByDistrictId(Long districtId) {
        return wardRespository.findByDistrictId(districtId)
                .stream()
                .map(ward -> {
                    WardResponse wardResponse = WardResponse.builder()
                            .wardCode(ward.getWardCode())
                            .wardName(ward.getWardName())
                            .build();
//                    if(ward.getDistrict() != null && !ward.getDistrict().getId().isEmpty()){
//                        wardResponse.setDistrictId(ward.getDistrict().getId());
//                        wardResponse.setDistrictCode(ward.getDistrict().getDistrictCode());
//                        wardResponse.setDistrictName(ward.getDistrict().getDistrictName());
//                    }
                    return wardResponse;
                })
                .toList();
    }

    @Override
    public Page<WardResponse> getListWard(PageRequest pageRequest) {
        return wardRespository.findAll(pageRequest).map(ward -> {
            WardResponse wardResponse = WardResponse.builder()
                    .id(ward.getId())
                    .wardCode(ward.getWardCode())
                    .wardName(ward.getWardName())
                    .build();
//            if(ward.getDistrict() != null && !ward.getDistrict().getId().isEmpty()){
//                wardResponse.setDistrictId(ward.getDistrict().getId());
//                wardResponse.setDistrictCode(ward.getDistrict().getDistrictCode());
//                wardResponse.setDistrictName(ward.getDistrict().getDistrictName());
//            }
            wardResponse.setCreatedAt(ward.getCreatedAt());
            wardResponse.setModifiedAt(ward.getModifiedAt());
            return wardResponse;
        });
    }

    @Override
    public Ward updateWard(Long wardId, WardDTO wardDTO) {
        User user = userRepository.getReferenceById(wardDTO.getCreatedById());
        Ward existstingWard = wardRespository.getReferenceById(wardId);
        District district = districtRespository.getReferenceById(wardDTO.getDistrictId());
        existstingWard.setWardCode(wardDTO.getWardCode());
        existstingWard.setWardName(wardDTO.getWardName());
        existstingWard.setDistrict(district);
        existstingWard.setModifiedByUser(user);
        wardRespository.save(existstingWard);
        return existstingWard;
    }

    @Override
    public WardResponse getWardById(Long id) {
        Ward existstingWard = wardRespository.getReferenceById(id);
        return WardResponse.builder()
                .id(existstingWard.getId())
                .wardCode(existstingWard.getWardCode())
                .wardName(existstingWard.getWardName())
                .build();
    }

    @Override
    public void deleteWard(Long id) {
        wardRespository.deleteById(id);
    }
}
