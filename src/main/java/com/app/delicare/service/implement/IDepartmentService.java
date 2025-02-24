package com.app.delicare.service.implement;

import com.app.delicare.dtos.DepartmentDTO;
import com.app.delicare.responses.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IDepartmentService {
    DepartmentResponse createDepartment(DepartmentDTO departmentDTO);
    List<DepartmentResponse> getAllDepartment();
    Page<DepartmentResponse> getListDepartment(PageRequest pageRequest);
    DepartmentResponse getDepartmentById(Long Id);
    DepartmentResponse updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
    void updateDepartment(Long id);
}
