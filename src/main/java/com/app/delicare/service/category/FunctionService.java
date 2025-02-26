package com.app.delicare.service.category;

import com.app.delicare.dtos.category.FunctionDTO;
import com.app.delicare.responses.category.FunctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FunctionService implements IFunctionService {
    @Override
    public FunctionResponse createFunction(FunctionDTO functionDTO) {
        return null;
    }

    @Override
    public List<FunctionResponse> getAllFunction() {
        return List.of();
    }

    @Override
    public Page<FunctionResponse> getListFunction(PageRequest pageRequest) {
        return null;
    }

    @Override
    public FunctionResponse updateFunction(Long id, FunctionDTO functionDTO) {
        return null;
    }

    @Override
    public FunctionResponse getFunctionById(Long id) {
        return null;
    }

    @Override
    public void deleteFunction(Long id) {

    }
}
