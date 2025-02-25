package com.app.delicare.service.implement;

import com.app.delicare.dtos.category.StageDTO;
import com.app.delicare.responses.category.StageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IStageService {
    StageResponse createStage(StageDTO stageDTO);
    List<StageResponse> getAllStage();
    Page<StageResponse> getListStage(PageRequest pageRequest);
    StageResponse updateStage(Long stageId, StageDTO stageDTO);
    StageResponse getStageById(Long id);
    void deleteStage(Long id);
}
