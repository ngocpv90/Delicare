package com.app.delicare.service;

import com.app.delicare.entitys.Role;
import com.app.delicare.dtos.RoleDTO;
import com.app.delicare.entitys.User;
import com.app.delicare.mappers.RoleMapper;
import com.app.delicare.repositories.RoleRepository;
import com.app.delicare.repositories.UserRepository;
import com.app.delicare.responses.RoleResponse;
import com.app.delicare.service.implement.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
    @Override
    public RoleResponse createRole(RoleDTO roleDTO) {
        User user = userRepository.getReferenceById(roleDTO.getCreatedById());
        Role newRole = roleMapper.mapEntityToModel(roleDTO);
        newRole.setCreatedByUser(user);
        roleRepository.save(newRole);
        roleRepository.flush();
        return roleMapper.mapResponseToEntity(newRole);
    }

    @Override
    public List<RoleResponse> getAllRole() {
        return roleRepository.findAll().stream().map(role -> {
            return roleMapper.mapResponseToEntity(role);
        }).toList();
    }

    @Override
    public Page<RoleResponse> getListRole(PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest).map(role -> {
            return roleMapper.mapResponseToEntity(role);
        });
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role existstingRole = roleRepository.getReferenceById(id);
        return roleMapper.mapResponseToEntity(existstingRole);
    }

    @Override
    public RoleResponse updateRole(Long roleId, RoleDTO roleDTO) {
        User user = userRepository.getReferenceById(roleDTO.getCreatedById());
        Role existstingRole = roleRepository.getReferenceById(roleId);
        existstingRole.setRoleCode(roleDTO.getRoleCode());
        existstingRole.setRoleName(roleDTO.getRoleName());
        existstingRole.setModifiedByUser(user);
        roleRepository.save(existstingRole);
        return roleMapper.mapResponseToEntity(existstingRole);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
