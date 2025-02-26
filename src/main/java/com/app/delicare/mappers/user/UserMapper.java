package com.app.delicare.mappers.user;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.user.UserDTO;
import com.app.delicare.entitys.category.Department;
import com.app.delicare.entitys.category.Group;
import com.app.delicare.entitys.category.Role;
import com.app.delicare.entitys.category.Title;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.category.RoleMapper;
import com.app.delicare.mappers.category.TitleMapper;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.mappers.category.DepartmentMapper;
import com.app.delicare.mappers.category.GroupMapper;
import com.app.delicare.repositories.category.DepartmentRepository;
import com.app.delicare.repositories.category.GroupRepository;
import com.app.delicare.repositories.category.RoleRepository;
import com.app.delicare.repositories.category.TitleRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper extends BaseMapper {
    private final TitleRepository titleRepository;
    private final GroupRepository groupRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TitleMapper titleMapper;
    private final GroupMapper groupMapper;
    private final DepartmentMapper departmentMapper;
    private final RoleMapper roleMapper;


    public User mapModelToEntity(UserDTO userDTO){
        User userMapper = User.builder()
                .userName(userDTO.getUserName())
                .fullName(userDTO.getFullName())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        if(!CommonUtils.isNullOrEmpty(userDTO.getTitleId())){
            Title title = titleRepository.getReferenceById(userDTO.getTitleId());
            userMapper.setTitle(title);
        }
        if(!CommonUtils.isNullOrEmpty(userDTO.getGroupId())){
            Group group = groupRepository.getReferenceById(userDTO.getGroupId());
            userMapper.setGroup(group);
        }
        if(!CommonUtils.isNullOrEmpty(userDTO.getDepartmentId())){
            Department department = departmentRepository.getReferenceById(userDTO.getDepartmentId());
            userMapper.setDepartment(department);
        }
        if(!CommonUtils.isNullOrEmpty(userDTO.getRoleId())){
            Role role = roleRepository.getReferenceById(userDTO.getRoleId());
            userMapper.setRole(role);
        }
        if(!CommonUtils.isNullOrEmpty(userDTO.getCreatedById())){
            User user = userRepository.getReferenceById(userDTO.getCreatedById());
            userMapper.setCreatedByUser(user);
            userMapper.setModifiedByUser(user);
        }
        userMapper.setDeleted(userDTO.getDeleted());
        userMapper.setDescription(userDTO.getDescription());
        userMapper.setStatus(userDTO.getStatus());
        return userMapper;
    }

    public UserResponse mapResponseToEntity(User user){
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        Optional.ofNullable(user)
                .map(User::getTitle)
                .ifPresent(title -> {
                    userResponse.setTitleResponse(titleMapper.mapResponseToEntity(user.getTitle()));
                });
        Optional.ofNullable(user)
                .map(User::getGroup)
                .ifPresent(group -> {
                    userResponse.setGroupResponse(groupMapper.mapResponseToEntity(user.getGroup()));
                });
        Optional.ofNullable(user)
                .map(User::getDepartment)
                .ifPresent(department -> {
                    userResponse.setDepartmentResponse(departmentMapper.mapResponseToEntity(user.getDepartment()));
                });
        Optional.ofNullable(user)
                .map(User::getRole)
                .ifPresent(role -> {
                    userResponse.setRoleResponse(roleMapper.mapResponseToEntity(user.getRole()));
                });
        Optional.ofNullable(user)
                .map(User::getCreatedByUser)
                .ifPresent(user1 -> {
                    userResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(user.getCreatedByUser()));
                });
        Optional.ofNullable(user)
                .map(User::getCreatedByUser)
                .ifPresent(user1 -> {
                    userResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(user.getModifiedByUser()));
                });
        userResponse.setDeleted(user.getDeleted());
        userResponse.setDescription(user.getDescription());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setModifiedAt(user.getModifiedAt());
        userResponse.setStatus(user.getStatus());
        return  userResponse;
    }

    public UserResponse mapResponseToEntityLogin(User user){
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return  userResponse;
    }
}
