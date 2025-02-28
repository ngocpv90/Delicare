package com.app.delicare.service.category;

import com.app.delicare.dtos.category.ActionDTO;
import com.app.delicare.entitys.category.Action;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.category.ActionMapper;
import com.app.delicare.repositories.category.ActionRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.category.ActionResponse;
import com.app.delicare.specification.category.ActionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionService implements IActionService {
    private final UserRepository userRepository;
    private final ActionMapper actionMapper;
    private final ActionRepository actionRepository;

    @Override
    public ActionResponse createAction(ActionDTO actionDTO) {
        try {
            Action action = actionMapper.mapEntityToModel(actionDTO);
            actionRepository.save(action);
            actionRepository.flush();
            return actionMapper.mapResponseToEntity(action);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ActionResponse> getAllAction() {
        Specification<Action> specification = Specification.where(ActionSpecification.isNotDeleted());
        return actionRepository.findAll(specification).stream().map(menu -> {
                    return  actionMapper.mapResponseToEntity(menu);
                })
                .toList();
    }

    @Override
    public Page<ActionResponse> getListAction(PageRequest pageRequest) {
        Specification<Action> specification = Specification.where(ActionSpecification.isNotDeleted());
        return actionRepository.findAll(specification, pageRequest).map(menu -> {
            return actionMapper.mapResponseToEntity(menu);
        });
    }

    @Override
    public ActionResponse updateAction(Long id, ActionDTO actionDTO) {
        User userModified = userRepository.getReferenceById(actionDTO.getModifiedById());
        Action action = actionMapper.mapEntityToModel(actionDTO);
        action.setId(id);
        action.setModifiedByUser(userModified);
        actionRepository.save(action);
        return actionMapper.mapResponseToEntity(action);
    }

    @Override
    public ActionResponse getActionById(Long id) {
        Action action = actionRepository.getReferenceById(id);
        return actionMapper.mapResponseToEntity(action);
    }

    @Override
    public void deleteAction(Long id) {

    }
}
