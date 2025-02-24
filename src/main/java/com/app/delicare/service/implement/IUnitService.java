package com.app.delicare.service.implement;

import com.app.delicare.dtos.TitleDTO;
import com.app.delicare.dtos.UnitDTO;
import com.app.delicare.responses.TitleResponse;
import com.app.delicare.responses.UnitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUnitService {
    UnitResponse createUnit(UnitDTO unitDTO);
    List<UnitResponse> getAllUnit();
    Page<UnitResponse> getListPageUnit(PageRequest pageRequest);
    UnitResponse getUnitById(Long Id);
    UnitResponse updateUnit(Long id, UnitDTO unitDTO);
    void deleteUnitById(Long id);
}
