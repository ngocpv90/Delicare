package com.app.delicare.mappers;

import com.app.delicare.dtos.RoleDTO;
import com.app.delicare.entitys.Province;
import com.app.delicare.entitys.Role;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleMapper extends BaseMapper {
    public Role mapEntityToModel(RoleDTO roleDTO){
        Role role = Role.builder()
                .roleCode(roleDTO.getRoleCode())
                .roleName(roleDTO.getRoleName())
                .build();
        role.setStatus(roleDTO.getStatus());
        return role;
    }
    public Role mapEntityToModel(Long id,RoleDTO roleDTO){
        Role role = mapEntityToModel(roleDTO);
        role.setId(id);
        return role;
    }
    public RoleResponse mapResponseToEntity(Role role){
        RoleResponse roleResponse = RoleResponse.builder()
                .roleCode(role.getRoleCode())
                .roleName(role.getRoleName())
                .build();
        Optional.ofNullable(role)
                .map(Role::getCreatedByUser)
                .ifPresent(user1 -> {
                    roleResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(role.getCreatedByUser()));
                });
        Optional.ofNullable(role)
                .map(Role::getCreatedByUser)
                .ifPresent(user1 -> {
                    roleResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(role.getModifiedByUser()));
                });
        roleResponse.setDeleted(role.getDeleted());
        roleResponse.setDescription(role.getDescription());
        roleResponse.setCreatedAt(role.getCreatedAt());
        roleResponse.setCreatedAt(role.getCreatedAt());
        roleResponse.setModifiedAt(role.getModifiedAt());
        roleResponse.setStatus(role.getStatus());
        return roleResponse;
    }
}
