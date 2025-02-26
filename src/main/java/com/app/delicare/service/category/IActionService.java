package com.app.delicare.service.category;

import com.app.delicare.dtos.category.ActionDTO;
import com.app.delicare.responses.category.ActionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IActionService {
    ActionResponse createAction(ActionDTO actionDTO);
    List<ActionResponse> getAllAction();
    Page<ActionResponse> getListAction(PageRequest pageRequest);
    ActionResponse updateAction(Long id, ActionDTO actionDTO);
    ActionResponse getActionById(Long id);
    void deleteAction(Long id);
}
