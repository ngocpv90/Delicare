package com.app.delicare.mappers.category;

import com.app.delicare.dtos.category.ActionDTO;
import com.app.delicare.entitys.category.Action;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.category.ActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ActionMapper extends BaseMapper {

    public Action mapEntityToModel(ActionDTO actionDTO){
        Action action = Action.builder()
                .actionCode(actionDTO.getActionCode())
                .actionName(actionDTO.getActionName())
                .isDisplay(actionDTO.getIsDisplay())
                .sort(actionDTO.getSort())
                .build();
        action.setStatus(actionDTO.getStatus());
        return action;
    }

    public ActionResponse mapResponseToEntity(Action action){
        ActionResponse actionResponse = ActionResponse.builder()
                .id(action.getId())
                .actionCode(action.getActionCode())
                .actionName(action.getActionName())
                .isDisplay(action.getIsDisplay())
                .sort(action.getSort())
                .build();

        Optional.ofNullable(action)
                .map(Action::getCreatedByUser)
                .ifPresent(user -> {
                    actionResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(action.getCreatedByUser()));
                });
        Optional.ofNullable(action)
                .map(Action::getModifiedByUser)
                .ifPresent(user -> {
                    actionResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(action.getModifiedByUser()));
                });
        actionResponse.setDeleted(action.getDeleted());
        actionResponse.setDescription(action.getDescription());
        actionResponse.setCreatedAt(action.getCreatedAt());
        actionResponse.setCreatedAt(action.getCreatedAt());
        actionResponse.setModifiedAt(action.getModifiedAt());
        actionResponse.setStatus(action.getStatus());
        return actionResponse;
    }
}
