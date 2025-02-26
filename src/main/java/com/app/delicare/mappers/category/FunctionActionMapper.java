package com.app.delicare.mappers.category;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.category.FunctionActionDTO;
import com.app.delicare.entitys.category.Action;
import com.app.delicare.entitys.category.Function;
import com.app.delicare.entitys.category.FunctionAction;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.category.ActionRepository;
import com.app.delicare.repositories.category.FunctionRepository;
import com.app.delicare.responses.category.FunctionActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FunctionActionMapper extends BaseMapper {
    private final FunctionRepository functionRepository;
    private final ActionRepository actionRepository;
    private final FunctionMapper functionMapper;
    private final ActionMapper actionMapper;

    public FunctionAction mapEntityToModel(FunctionActionDTO functionActionDTO){
        FunctionAction functionAction = FunctionAction.builder()
                .build();
        if(!CommonUtils.isNullOrEmpty(functionActionDTO.getFunctionId())){
            Function function = functionRepository.getReferenceById(functionActionDTO.getFunctionId());
            functionAction.setFunction(function);
        }
        if(!CommonUtils.isNullOrEmpty(functionActionDTO.getActionId())){
            Action action = actionRepository.getReferenceById(functionActionDTO.getActionId());
            functionAction.setAction(action);
        }
        functionAction.setStatus(functionActionDTO.getStatus());
        return functionAction;
    }

    public FunctionActionResponse mapResponseToEntity(FunctionAction functionAction){
        FunctionActionResponse functionResponse = FunctionActionResponse.builder()
                .id(functionAction.getId())
                .build();

        Optional.ofNullable(functionAction)
                .map(FunctionAction::getFunction)
                .ifPresent(user -> {
                    functionResponse.setFunctionResponse(functionMapper.mapResponseToEntity(functionAction.getFunction()));
                });
        Optional.ofNullable(functionAction)
                .map(FunctionAction::getAction)
                .ifPresent(user -> {
                    functionResponse.setActionResponse(actionMapper.mapResponseToEntity(functionAction.getAction()));
                });
        Optional.ofNullable(functionAction)
                .map(FunctionAction::getCreatedByUser)
                .ifPresent(user -> {
                    functionResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(functionAction.getCreatedByUser()));
                });
        Optional.ofNullable(functionAction)
                .map(FunctionAction::getModifiedByUser)
                .ifPresent(user -> {
                    functionResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(functionAction.getModifiedByUser()));
                });
        functionResponse.setDeleted(functionAction.getDeleted());
        functionResponse.setDescription(functionAction.getDescription());
        functionResponse.setCreatedAt(functionAction.getCreatedAt());
        functionResponse.setCreatedAt(functionAction.getCreatedAt());
        functionResponse.setModifiedAt(functionAction.getModifiedAt());
        functionResponse.setStatus(functionAction.getStatus());
        return functionResponse;
    }
}
