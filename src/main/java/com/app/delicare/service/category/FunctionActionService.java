package com.app.delicare.service.category;

import com.app.delicare.dtos.category.FunctionActionDTO;
import com.app.delicare.responses.category.FunctionActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FunctionActionService implements IFuctionActionService {
    @Override
    public FunctionActionResponse createMenu(FunctionActionDTO functionActionDTO) {
        return null;
    }

    @Override
    public List<FunctionActionResponse> getAllMenu() {
        return List.of();
    }

    @Override
    public Page<FunctionActionResponse> getListMenu(PageRequest pageRequest) {
        return null;
    }

    @Override
    public FunctionActionResponse updateMenu(Long id, FunctionActionDTO functionActionDTO) {
        return null;
    }

    @Override
    public FunctionActionResponse getMenuyId(Long id) {
        return null;
    }

    @Override
    public void deleteMenu(Long id) {

    }
}
