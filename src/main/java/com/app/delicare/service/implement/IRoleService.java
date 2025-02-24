package com.app.delicare.service.implement;

import com.app.delicare.dtos.RoleDTO;
import com.app.delicare.responses.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRoleService {
    RoleResponse createRole(RoleDTO roleDTO);
    List<RoleResponse> getAllRole();
    Page<RoleResponse> getListRole(PageRequest pageRequest);
    RoleResponse getRoleById(Long id);
    RoleResponse updateRole(Long roleId, RoleDTO roleDTO);
    void deleteRole(Long id);
}
