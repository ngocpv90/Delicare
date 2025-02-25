package com.app.delicare.service.implement;

import com.app.delicare.entitys.address.Ward;
import com.app.delicare.dtos.address.WardDTO;
import com.app.delicare.responses.address.WardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IWardService {
    WardResponse createWard(WardDTO wardDTO);
    List<Ward> getAllWard();
    List<WardResponse> getWardByDistrictId(Long districtId);
    Page<WardResponse> getListWard(PageRequest pageRequest);
    Ward updateWard(Long wardId, WardDTO wardDTO);
    WardResponse getWardById(Long id);
    void deleteWard(Long id);
}
