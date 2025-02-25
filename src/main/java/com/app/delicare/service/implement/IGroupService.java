package com.app.delicare.service.implement;

import com.app.delicare.dtos.category.GroupDTO;
import com.app.delicare.responses.category.GroupResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IGroupService {
    GroupResponse createGroup(GroupDTO groupDTO);
    List<GroupResponse> getAllGroup();
    List<GroupResponse> getGroupByDepartmentId(Long departmentId);
    Page<GroupResponse> getListGroup(PageRequest pageRequest);
    GroupResponse updateGroup(Long groupId, GroupDTO groupDTO);
    GroupResponse getGroupById(Long id);
    void deleteGroup(Long id);
}
