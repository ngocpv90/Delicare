package com.app.delicare.service.category;

import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.category.Department;
import com.app.delicare.entitys.category.Group;
import com.app.delicare.dtos.category.GroupDTO;
import com.app.delicare.entitys.users.User;
import com.app.delicare.mappers.category.GroupMapper;
import com.app.delicare.repositories.category.DepartmentRepository;
import com.app.delicare.repositories.category.GroupRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.specification.category.GroupSpecification;
import com.app.delicare.responses.category.GroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public GroupResponse createGroup(GroupDTO groupDTO) {
        Department department = departmentRepository.getReferenceById(groupDTO.getDepartmentId());
        User user = userRepository.getReferenceById(groupDTO.getCreatedById());
        Group group = groupMapper.mapEntityToModel(groupDTO, department);
        group.setCreatedByUser(user);
        groupRepository.save(group);
        groupRepository.flush();;
        return groupMapper.mapResponseToEntity(group);
    }

    @Override
    public List<GroupResponse> getAllGroup() {
        Specification<Group> specification = Specification.where(GroupSpecification.isNotDeleted());
        List<Group> groups = groupRepository.findAll(specification);
        return groupMapper.mapResponseToEntity(groups);
    }

    @Override
    public List<GroupResponse> getGroupByDepartmentId(Long departmentId) {
        Specification<Group> specification = Specification.where(GroupSpecification.isNotDeleted());
        specification.and(GroupSpecification.hasDepartmentId(departmentId, ESpecification.EQUAL.getValue()));
        List<Group> groups = groupRepository.findAll(specification);
        return groupMapper.mapResponseToEntity(groups);
    }

    @Override
    public Page<GroupResponse> getListGroup(PageRequest pageRequest) {
        Specification<Group> specification = Specification.where(GroupSpecification.isNotDeleted());
        return groupRepository.findAll(specification, pageRequest).map(group -> {
            return  groupMapper.mapResponseToEntity(group);
        });
    }

    @Override
    public GroupResponse updateGroup(Long groupId, GroupDTO groupDTO) {
        Department department= departmentRepository.getReferenceById(groupDTO.getDepartmentId());
        User user = userRepository.getReferenceById(groupDTO.getModifiedById());
        Group existstingGroup = groupRepository.getReferenceById(groupId);
        existstingGroup.setGroupCode(groupDTO.getGroupCode());
        existstingGroup.setGroupName(groupDTO.getGroupName());
        existstingGroup.setStatus(groupDTO.getStatus());
        existstingGroup.setCreatedByUser(user);
        groupRepository.save(existstingGroup);
        return groupMapper.mapResponseToEntity(existstingGroup);
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        Group group = groupRepository.getReferenceById(id);
        return groupMapper.mapResponseToEntity(group);
    }

    @Override
    public void deleteGroup(Long id) {

    }
}
