package com.app.delicare.mappers;

import com.app.delicare.entitys.category.Department;
import com.app.delicare.dtos.category.DepartmentDTO;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.category.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentMapper extends BaseMapper {
    public Department mapEntityToModel(DepartmentDTO departmentDTO){
        Department department = Department.builder()
                .departmentCode(departmentDTO.getDepartmentCode())
                .departmentName(departmentDTO.getDepartmentName())
                .build();
        department.setStatus(departmentDTO.getStatus());
        return department;
    }

    public DepartmentResponse mapResponseToEntity(Department department){
        DepartmentResponse departmentResponse = DepartmentResponse.builder()
                .id(department.getId())
                .departmentCode(department.getDepartmentCode())
                .departmentName(department.getDepartmentName())
                .build();

        Optional.ofNullable(department)
                .map(Department::getCreatedByUser)
                .ifPresent(user -> {
                    departmentResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(department.getCreatedByUser()));
                });

        Optional.ofNullable(department)
                .map(Department::getModifiedByUser)
                .ifPresent(user -> {
                    departmentResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(department.getModifiedByUser()));
                });

        departmentResponse.setDeleted(department.getDeleted());
        departmentResponse.setDescription(department.getDescription());
        departmentResponse.setCreatedAt(department.getCreatedAt());
        departmentResponse.setCreatedAt(department.getCreatedAt());
        departmentResponse.setModifiedAt(department.getModifiedAt());
        departmentResponse.setStatus(department.getStatus());
        return departmentResponse;
    }

    public List<DepartmentResponse> mapResponseToEntity(List<Department> departments){
        return departments.stream().map(department -> {
            return mapResponseToEntity(department);
        }).toList();

    }
}
