package com.app.delicare.mappers.category;
import com.app.delicare.dtos.category.FunctionDTO;
import com.app.delicare.entitys.category.Function;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.category.FunctionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class FunctionMapper extends BaseMapper{

    public Function mapEntityToModel(FunctionDTO functionDTO){
        Function menu = Function.builder()
                .functionCode(functionDTO.getFunctionCode())
                .functionName(functionDTO.getFunctionName())
                .build();
        menu.setStatus(functionDTO.getStatus());
        return menu;
    }

    public FunctionResponse mapResponseToEntity(Function functions){
        FunctionResponse functionResponse = FunctionResponse.builder()
                .id(functions.getId())
                .functionCode(functions.getFunctionCode())
                .functionName(functions.getFunctionName())
                .build();

        Optional.ofNullable(functions)
                .map(Function::getCreatedByUser)
                .ifPresent(user -> {
                    functionResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(functions.getCreatedByUser()));
                });
        Optional.ofNullable(functions)
                .map(Function::getModifiedByUser)
                .ifPresent(user -> {
                    functionResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(functions.getModifiedByUser()));
                });
        functionResponse.setDeleted(functions.getDeleted());
        functionResponse.setDescription(functions.getDescription());
        functionResponse.setCreatedAt(functions.getCreatedAt());
        functionResponse.setCreatedAt(functions.getCreatedAt());
        functionResponse.setModifiedAt(functions.getModifiedAt());
        functionResponse.setStatus(functions.getStatus());
        return functionResponse;
    }
}
