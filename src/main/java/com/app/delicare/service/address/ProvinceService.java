package com.app.delicare.service.address;

import com.app.delicare.entitys.address.Province;
import com.app.delicare.dtos.address.ProvinceDTO;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.address.ProvinceMapper;
import com.app.delicare.repositories.address.ProvinceRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.address.ProvinceResponse;
import com.app.delicare.specification.address.ProvinceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProvinceService implements IProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;
    private final UserRepository userRepository;

    @Override
    public ProvinceResponse createProvince(ProvinceDTO provinceDTO) {
        Province province = provinceMapper.mapEntityToModel(provinceDTO);
        provinceRepository.save(province);
        return provinceMapper.mapResponseToEntity(province);
    }

    @Override
    public List<ProvinceResponse> getAllProvince() {
        Specification<Province> specification = Specification.where(ProvinceSpecification.isNotDeleted());
        return provinceRepository.findAll(specification).stream().map(province -> {
                    return  provinceMapper.mapResponseToEntity(province);
                })
                .toList();
    }

    @Override
    public Page<ProvinceResponse> getListProvince(PageRequest pageRequest) {
        Specification<Province> specification = Specification.where(ProvinceSpecification.isNotDeleted());
        return provinceRepository.findAll(specification, pageRequest).map(menu -> {
            return provinceMapper.mapResponseToEntity(menu);
        });
    }

    @Override
    public ProvinceResponse updateProvince(Long id, ProvinceDTO provinceDTO) {
        User userModified = userRepository.getReferenceById(provinceDTO.getModifiedById());
        Province province = provinceRepository.getReferenceById(id);
        province.setId(id);
        province.setModifiedByUser(userModified);
        provinceRepository.save(province);
        return provinceMapper.mapResponseToEntity(province);
    }

    @Override
    public ProvinceResponse getProvinceById(Long id) {
        Province existstingProvince = provinceRepository.getReferenceById(id);
        return ProvinceResponse.builder()
                .id(existstingProvince.getId())
                .provinceName(existstingProvince.getProvinceCode())
                .provinceCode(existstingProvince.getProvinceName())
                .build();
    }

    @Override
    public void deleteProvince(Long provinceId) {
        provinceRepository.deleteById(provinceId);
    }
}
