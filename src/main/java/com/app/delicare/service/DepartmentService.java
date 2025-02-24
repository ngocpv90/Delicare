package com.app.delicare.service;

import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.Department;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.DepartmentMapper;
import com.app.delicare.dtos.DepartmentDTO;
import com.app.delicare.repositories.DepartmentRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.specification.DepartmentSpecification;
import com.app.delicare.responses.DepartmentResponse;
import com.app.delicare.service.implement.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponse createDepartment(DepartmentDTO departmentDTO) {
        User user = userRepository.getReferenceById(departmentDTO.getCreatedById());
        Department department = departmentMapper.mapEntityToModel(departmentDTO);
        department.setCreatedByUser(user);
        departmentRepository.save(department);
        departmentRepository.flush();;
        return departmentMapper.mapResponseToEntity(department);
    }

    @Override
    public List<DepartmentResponse> getAllDepartment() {
        Specification<Department> specification = Specification.where(DepartmentSpecification.isNotDeleted());
        List<Department> departments = departmentRepository.findAll(specification);
        return departmentMapper.mapResponseToEntity(departments);
    }

    @Override
    public Page<DepartmentResponse> getListDepartment(PageRequest pageRequest) {
        Specification<Department> specification = Specification.where(DepartmentSpecification.isNotDeleted());
        return departmentRepository.findAll(specification, pageRequest).map(department -> {
            return  departmentMapper.mapResponseToEntity(department);
        });
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.getReferenceById(id);
        return departmentMapper.mapResponseToEntity(department);
    }

    @Override
    public DepartmentResponse updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        User user = userRepository.getReferenceById(departmentDTO.getCreatedById());
        Department existstingDepartment = departmentRepository.getReferenceById(departmentId);
        existstingDepartment.setDepartmentCode(departmentDTO.getDepartmentCode());
        existstingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        existstingDepartment.setStatus(departmentDTO.getStatus());
        existstingDepartment.setModifiedByUser(user);
        departmentRepository.save(existstingDepartment);
        return departmentMapper.mapResponseToEntity(existstingDepartment);
    }

    @Override
    public void updateDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
