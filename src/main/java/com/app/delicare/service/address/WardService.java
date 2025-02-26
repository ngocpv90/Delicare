package com.app.delicare.service.address;

import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.address.District;
import com.app.delicare.entitys.address.Province;
import com.app.delicare.entitys.user.User;
import com.app.delicare.entitys.address.Ward;
import com.app.delicare.dtos.address.WardDTO;
import com.app.delicare.mappers.address.WardMapper;
import com.app.delicare.repositories.address.DistrictRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.repositories.address.WardRepository;
import com.app.delicare.responses.address.WardResponse;
import com.app.delicare.specification.address.ProvinceSpecification;
import com.app.delicare.specification.address.WardSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WardService implements IWardService {
    private final WardRepository wardRepository;
    private final WardMapper wardMapper;
    private final UserRepository userRepository;
    @Override
    public WardResponse createWard(WardDTO wardDTO) {
        Ward ward = wardMapper.mapEntityToModel(wardDTO);
        wardRepository.save(ward);
        wardRepository.flush();
        return wardMapper.mapResponseToEntity(ward);
    }

    @Override
    public List<WardResponse> getAllWard() {
        Specification<Ward> specification = Specification.where(WardSpecification.isNotDeleted());
        return wardRepository.findAll(specification).stream().map(ward -> {
                    return  wardMapper.mapResponseToEntity(ward);
                })
                .toList();
    }

    @Override
    public List<WardResponse> getWardByDistrictId(Long districtId) {
        Specification<Ward> specification = Specification.where(WardSpecification.isNotDeleted());
        specification.and(WardSpecification.hasDistrictId(districtId, ESpecification.EQUAL.getValue()));
        return wardRepository.findAll(specification).stream().map(ward -> {
                    return  wardMapper.mapResponseToEntity(ward);
                })
                .toList();
    }

    @Override
    public Page<WardResponse> getListWard(PageRequest pageRequest) {
        Specification<Ward> specification = Specification.where(WardSpecification.isNotDeleted());
        return wardRepository.findAll(specification, pageRequest).map(menu -> {
            return wardMapper.mapResponseToEntity(menu);
        });
    }

    @Override
    public WardResponse updateWard(Long id, WardDTO wardDTO) {
        User userModified = userRepository.getReferenceById(wardDTO.getModifiedById());
        Ward ward = wardRepository.getReferenceById(id);
        ward.setId(id);
        ward.setModifiedByUser(userModified);
        wardRepository.save(ward);
        return wardMapper.mapResponseToEntity(ward);
    }

    @Override
    public WardResponse getWardById(Long id) {
        Ward ward = wardRepository.getReferenceById(id);
        return wardMapper.mapResponseToEntity(ward);
    }

    @Override
    public void deleteWard(Long id) {
        wardRepository.deleteById(id);
    }
}
