package com.app.delicare.service.category;

import com.app.delicare.dtos.category.UnitDTO;
import com.app.delicare.entitys.category.Units;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.category.UnitMapper;
import com.app.delicare.repositories.category.UnitRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.UnitResponse;
import com.app.delicare.specification.category.UnitSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UnitService implements IUnitService {
    private final UserRepository userRepository;
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public UnitResponse createUnit(UnitDTO unitDTO) {
        try{
            Units unit = unitMapper.mapEntityToModel(unitDTO);
            unitRepository.save(unit);
            userRepository.flush();
            return unitMapper.mapResponseToEntity(unit);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UnitResponse> getAllUnit() {
        Specification<Units> specification = Specification.where(UnitSpecification.isNotDeleted());
        return unitRepository.findAll(specification).stream().map(unit -> {
                    return unitMapper.mapResponseToEntity(unit);
                })
                .toList();
    }

    @Override
    public Page<UnitResponse> getListPageUnit(PageRequest pageRequest) {
        Specification<Units> specification = Specification.where(UnitSpecification.isNotDeleted());
        return unitRepository.findAll(specification, pageRequest).map(unit -> {
            return unitMapper.mapResponseToEntity(unit);
        });
    }

    @Override
    public UnitResponse getUnitById(Long id) {
        Units units = unitRepository.getReferenceById(id);
        return unitMapper.mapResponseToEntity(units);
    }

    @Override
    public UnitResponse updateUnit(Long id, UnitDTO unitDTO) {
        User userModified = userRepository.getReferenceById(unitDTO.getModifiedById());
        Units units = unitMapper.mapEntityToModel(unitDTO);
        units.setId(id);
        units.setModifiedByUser(userModified);
        unitRepository.save(units);
        return unitMapper.mapResponseToEntity(units);
    }

    @Override
    public void deleteUnitById(Long id) {

    }
}
