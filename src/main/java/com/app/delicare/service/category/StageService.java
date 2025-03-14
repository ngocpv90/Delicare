package com.app.delicare.service.category;

import com.app.delicare.dtos.category.StageDTO;
import com.app.delicare.entitys.category.Stage;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.category.StageMapper;
import com.app.delicare.repositories.category.StageRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.category.StageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageService implements IStageService {
    private final StageRepository stageRepository;
    private final UserRepository userRepository;
    private final StageMapper stageMapper;
    @Override
    public StageResponse createStage(StageDTO stageDTO) {
        User user = userRepository.getReferenceById(stageDTO.getCreatedById());
        Stage newStage = stageMapper.mapEntityToModel(stageDTO);
        newStage.setCreatedByUser(user);
        stageRepository.save(newStage);
        return null;
    }

    @Override
    public List<StageResponse> getAllStage() {
        List<Stage> stateList =  stageRepository.findAll();
        return stageMapper.mapResponseToEntity(stateList);
    }

    @Override
    public Page<StageResponse> getListStage(PageRequest pageRequest) {
        return stageRepository.findAll(pageRequest).map(stage -> {
            return stageMapper.mapResponseToEntity(stage);
        });
    }

    @Override
    public StageResponse updateStage(Long stageId, StageDTO stageDTO) {
        User user = userRepository.getReferenceById(stageDTO.getModifiedById());
        Stage stage = stageRepository.getReferenceById(stageId);
        stage.setStageCode(stageDTO.getStageCode());
        stage.setStageName(stageDTO.getStateName());
        stage.setStageType(stageDTO.getStateType());
        stage.setModifiedByUser(user);
        stageRepository.save(stage);
        return stageMapper.mapResponseToEntity(stage);
    }

    @Override
    public StageResponse getStageById(Long id) {
        Stage stage = stageRepository.getReferenceById(id);
        return stageMapper.mapResponseToEntity(stage);
    }

    @Override
    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }
}
