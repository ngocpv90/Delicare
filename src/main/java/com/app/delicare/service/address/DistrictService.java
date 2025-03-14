package com.app.delicare.service.address;

import com.app.delicare.entitys.address.District;
import com.app.delicare.entitys.address.Province;
import com.app.delicare.dtos.address.DistrictDTO;
import com.app.delicare.mappers.address.DistrictMapper;
import com.app.delicare.repositories.address.DistrictRepository;
import com.app.delicare.repositories.address.ProvinceRepository;
import com.app.delicare.responses.address.DistrictResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DistrictService implements IDistrictService {
    private final DistrictRepository districtRespository;
    private final ProvinceRepository provinceRespository;
    private final DistrictMapper districtMapper;
    @Override
    public DistrictResponse createArea(DistrictDTO districtDTO) {
        District district = districtMapper.mapEntityToModel(districtDTO);
        districtRespository.save(district);
        return districtMapper.mapResponseToEntity(district);
    }

    @Override
    public List<DistrictResponse> getAllDistrict() {
        List<District> districts = districtRespository.findAll();
        return districtMapper.mapResponseToEntity(districts);
    }

    @Override
    public List<DistrictResponse> getDistrictByProvinceId(Long provinceId) {
        List<District> districts = districtRespository.findByProvinceId(provinceId);
        return districtMapper.mapResponseToEntity(districts);
    }

    @Override
    public Page<DistrictResponse> getListDistrict(PageRequest pageRequest) {
        return districtRespository.findAll(pageRequest).map(district -> {
            return districtMapper.mapResponseToEntity(district);
        });
    }

    @Override
    public DistrictResponse updateDistrict(Long districtId, DistrictDTO districtDTO) {
        District existstingDistrict = districtRespository.getReferenceById(districtId);
        Province province = provinceRespository.getReferenceById(districtDTO.getProvinceId());
        existstingDistrict.setDistrictCode(districtDTO.getDistrictCode());
        existstingDistrict.setDistrictName(districtDTO.getDistrictName());
        existstingDistrict.setProvince(province);
        districtRespository.save(existstingDistrict);
        return districtMapper.mapResponseToEntity(existstingDistrict);
    }

    @Override
    public DistrictResponse getDistrictById(Long id) {
        District existstingDistrict = districtRespository.getReferenceById(id);
        return DistrictResponse.builder()
                .id(existstingDistrict.getId())
                .districtName(existstingDistrict.getDistrictCode())
                .districtCode(existstingDistrict.getDistrictName())
                .build();
    }

    @Override
    public void deleteDistrict(Long id) {
        districtRespository.deleteById(id);
    }
}
