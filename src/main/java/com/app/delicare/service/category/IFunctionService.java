package com.app.delicare.service.category;

import com.app.delicare.dtos.category.FunctionDTO;
import com.app.delicare.responses.category.FunctionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IFunctionService {
    FunctionResponse createFunction(FunctionDTO functionDTO);
    List<FunctionResponse> getAllFunction();
    Page<FunctionResponse> getListFunction(PageRequest pageRequest);
    FunctionResponse updateFunction(Long id, FunctionDTO functionDTO);
    FunctionResponse getFunctionById(Long id);
    void deleteFunction(Long id);
}
