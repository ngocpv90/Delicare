package com.app.delicare.mappers.category;

import com.app.delicare.dtos.category.UnitDTO;
import com.app.delicare.entitys.category.Units;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.UnitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UnitMapper extends BaseMapper {

    public Units mapEntityToModel(UnitDTO unitDTO){
        Units title = Units.builder()
                .unitCode(unitDTO.getUnitCode())
                .unitName(unitDTO.getUnitName())
                .unitType(unitDTO.getUnitType())
                .build();
        title.setStatus(unitDTO.getStatus());
        title.setDescription(unitDTO.getDescription());
        return title;
    }

    public UnitResponse mapResponseToEntity(Units unit){
        UnitResponse titleResponse = UnitResponse.builder()
                .id(unit.getId())
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .unitType(unit.getUnitType())
                .build();
        Optional.ofNullable(unit)
                .map(Units::getCreatedByUser)
                .ifPresent(user1 -> {
                    titleResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(unit.getCreatedByUser()));
                });
        Optional.ofNullable(unit)
                .map(Units::getCreatedByUser)
                .ifPresent(user1 -> {
                    titleResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(unit.getModifiedByUser()));
                });
        titleResponse.setDeleted(unit.getDeleted());
        titleResponse.setDescription(unit.getDescription());
        titleResponse.setCreatedAt(unit.getCreatedAt());
        titleResponse.setCreatedAt(unit.getCreatedAt());
        titleResponse.setModifiedAt(unit.getModifiedAt());
        titleResponse.setStatus(unit.getStatus());
        return titleResponse;
    }
}
