package com.app.delicare.service;

import com.app.delicare.entitys.Province;
import com.app.delicare.dtos.ProvinceDTO;
import com.app.delicare.repositories.ProvinceRepository;
import com.app.delicare.responses.ProvinceResponse;
import com.app.delicare.service.implement.IProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProvinceService implements IProvinceService {
    private final ProvinceRepository provinceRespository;
    @Override
    public ProvinceResponse createProvince(ProvinceDTO provinceDTO) {
        Province province = Province.builder()
                .provinceCode(provinceDTO.getProvinceCode())
                .provinceCode(provinceDTO.getProvinceName())
                .build();
        provinceRespository.save(province);
        return null;
    }

    @Override
    public List<Province> getAllProvince() {
        return provinceRespository.findAll();
    }

    @Override
    public Page<ProvinceResponse> getListProvince(PageRequest pageRequest) {
        return provinceRespository.findAll(pageRequest).map(province -> {
            ProvinceResponse provinceResponse = ProvinceResponse.builder()
                    .provinceCode(province.getProvinceCode())
                    .provinceName(province.getProvinceName())
                    .build();
            return provinceResponse;
        });
    }

    @Override
    public Province updateProvince(Long provinceId, ProvinceDTO provinceDTO) {
        Province existstingProvince = provinceRespository.getReferenceById(provinceId);
        existstingProvince.setProvinceCode(provinceDTO.getProvinceCode());
        existstingProvince.setProvinceName(provinceDTO.getProvinceName());
        provinceRespository.save(existstingProvince);
        return existstingProvince;
    }

    @Override
    public ProvinceResponse getProvinceById(Long id) {
        Province existstingProvince = provinceRespository.getReferenceById(id);
        return ProvinceResponse.builder()
                .id(existstingProvince.getId())
                .provinceName(existstingProvince.getProvinceCode())
                .provinceCode(existstingProvince.getProvinceName())
                .build();
    }

    @Override
    public void deleteProvince(Long provinceId) {
        provinceRespository.deleteById(provinceId);
    }
}
