package com.app.delicare.mappers.category;

import com.app.delicare.dtos.category.GroupDTO;
import com.app.delicare.entitys.category.Department;
import com.app.delicare.entitys.category.Group;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.category.GroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GroupMapper extends BaseMapper {
    private final DepartmentMapper departmentMapper;

    public Group mapEntityToModel(GroupDTO groupDTO){
        Group group = Group.builder()
                .groupCode(groupDTO.getGroupCode())
                .groupName(groupDTO.getGroupName())
                .build();
        group.setStatus(groupDTO.getStatus());
        return group;
    }
    public Group mapEntityToModel(GroupDTO groupDTO, Department department){
        Group group = mapEntityToModel(groupDTO);
        group.setDepartment(department);
        return group;
    }
    public Group mapEntityToModel(Long id, GroupDTO groupDTO, Department department){
        Group group = mapEntityToModel(groupDTO, department);
        group.setId(id);
        return group;
    }
    public GroupResponse mapResponseToEntity(Group group){
        GroupResponse groupResponse = GroupResponse.builder()
                .id(group.getId())
                .groupCode(group.getGroupCode())
                .groupName(group.getGroupName())
                .build();

        Optional.ofNullable(group)
                .map(Group::getDepartment)
                .ifPresent(department -> {
                    groupResponse.setDepartmentResponse(departmentMapper.mapResponseToEntity(group.getDepartment()));
                });
        Optional.ofNullable(group)
                .map(Group::getCreatedByUser)
                .ifPresent(department -> {
                    groupResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(group.getCreatedByUser()));
                });
        Optional.ofNullable(group)
                .map(Group::getModifiedByUser)
                .ifPresent(department -> {
                    groupResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(group.getModifiedByUser()));
                });
        groupResponse.setDeleted(group.getDeleted());
        groupResponse.setDescription(group.getDescription());
        groupResponse.setCreatedAt(group.getCreatedAt());
        groupResponse.setCreatedAt(group.getCreatedAt());
        groupResponse.setModifiedAt(group.getModifiedAt());
        groupResponse.setStatus(group.getStatus());
        return groupResponse;
    }
    public List<GroupResponse> mapResponseToEntity(List<Group> groups){
        return groups.stream().map(group -> {
            return mapResponseToEntity(group);
        }).toList();
    }
}
