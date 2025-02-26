package com.app.delicare.service.address;

import com.app.delicare.entitys.address.Province;
import com.app.delicare.dtos.address.ProvinceDTO;
import com.app.delicare.responses.address.ProvinceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProvinceService {
    ProvinceResponse createProvince(ProvinceDTO provinceDTO);
    List<ProvinceResponse> getAllProvince();
    Page<ProvinceResponse> getListProvince(PageRequest pageRequest);
    ProvinceResponse updateProvince(Long id, ProvinceDTO provinceDTO);
    ProvinceResponse getProvinceById(Long id);
    void deleteProvince(Long id);
}
