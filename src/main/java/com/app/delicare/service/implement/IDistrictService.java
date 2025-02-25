package com.app.delicare.service.implement;

import com.app.delicare.dtos.address.DistrictDTO;
import com.app.delicare.responses.address.DistrictResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IDistrictService {
    DistrictResponse createArea(DistrictDTO districtDTO);
    List<DistrictResponse> getAllDistrict();
    List<DistrictResponse> getDistrictByProvinceId(Long provinceId);
    Page<DistrictResponse> getListDistrict(PageRequest pageRequest);
    DistrictResponse updateDistrict(Long districtId, DistrictDTO districtDTO);
    DistrictResponse getDistrictById(Long id);
    void deleteDistrict(Long id);
}
