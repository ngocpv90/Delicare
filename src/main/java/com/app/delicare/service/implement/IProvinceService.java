package com.app.delicare.service.implement;

import com.app.delicare.entitys.Province;
import com.app.delicare.dtos.ProvinceDTO;
import com.app.delicare.responses.ProvinceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProvinceService {
    ProvinceResponse createProvince(ProvinceDTO provinceDTO);
    List<Province> getAllProvince();
    Page<ProvinceResponse> getListProvince(PageRequest pageRequest);
    Province updateProvince(Long provinceId, ProvinceDTO provinceDTO);
    ProvinceResponse getProvinceById(Long id);
    void deleteProvince(Long provinceId);
}
