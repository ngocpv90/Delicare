package com.app.delicare.service.category;

import com.app.delicare.dtos.category.FunctionActionDTO;
import com.app.delicare.responses.category.FunctionActionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IFuctionActionService {
    FunctionActionResponse createMenu(FunctionActionDTO functionActionDTO);
    List<FunctionActionResponse> getAllMenu();
    Page<FunctionActionResponse> getListMenu(PageRequest pageRequest);
    FunctionActionResponse updateMenu(Long id, FunctionActionDTO functionActionDTO);
    FunctionActionResponse getMenuyId(Long id);
    void deleteMenu(Long id);
}
