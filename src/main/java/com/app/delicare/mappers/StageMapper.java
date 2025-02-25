package com.app.delicare.mappers;

import com.app.delicare.dtos.category.StageDTO;
import com.app.delicare.entitys.category.Stage;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.category.StageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StageMapper extends BaseMapper {
    public Stage mapEntityToModel(StageDTO stageDTO){
        Stage stage = Stage.builder()
                .stageCode(stageDTO.getStageCode())
                .stageName(stageDTO.getStateName())
                .stageType(stageDTO.getStateType())
                .build();
        stage.setStatus(stageDTO.getStatus());
        stage.setDescription(stageDTO.getDescription());
        return stage;
    }
    public Stage mapEntityToModel(Long id, StageDTO stageDTO){
        Stage stage = mapEntityToModel(stageDTO);
        stage.setId(id);
        return stage;
    }
    public StageResponse mapResponseToEntity(Stage state){
        StageResponse stageResponse = StageResponse.builder()
                .stageCode(state.getStageCode())
                .stageName(state.getStageName())
                .stageType(state.getStageType())
                .build();
        Optional.ofNullable(state)
                .map(Stage::getCreatedByUser)
                .ifPresent(user1 -> {
                    stageResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(state.getCreatedByUser()));
                });
        Optional.ofNullable(state)
                .map(Stage::getCreatedByUser)
                .ifPresent(user1 -> {
                    stageResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(state.getModifiedByUser()));
                });
        stageResponse.setDeleted(state.getDeleted());
        stageResponse.setDescription(state.getDescription());
        stageResponse.setCreatedAt(state.getCreatedAt());
        stageResponse.setCreatedAt(state.getCreatedAt());
        stageResponse.setModifiedAt(state.getModifiedAt());
        stageResponse.setStatus(state.getStatus());
        return stageResponse;
    }
    public List<StageResponse> mapResponseToEntity(List<Stage> stateList){
        return stateList.stream().map(stage -> {
            return mapResponseToEntity(stage);
        }).toList();
    }
}
