package com.app.delicare.mappers;
import com.app.delicare.dtos.UserHistoryDTO;
import com.app.delicare.entitys.*;
import com.app.delicare.dtos.UserDTO;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.UserHistoryResponse;
import com.app.delicare.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserHistoryMapper extends BaseMapper {
    private final TitleMapper titleMapper;
    private final GroupMapper groupMapper;
    private final DepartmentMapper departmentMapper;
    private final RoleMapper roleMapper;

    public UserHistory mapEntityToEntity(User user){
        UserHistory userHistory = UserHistory.builder()
                .userName(user.getUsername())
                .fullName(user.getFullName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .user(user)
                .build();
        Optional.ofNullable(user)
                .map(User::getTitle)
                .ifPresent(title -> {
                    userHistory.setTitle(user.getTitle());
                });
        Optional.ofNullable(user)
                .map(User::getGroup)
                .ifPresent(group -> {
                    userHistory.setGroup(user.getGroup());
                });
        Optional.ofNullable(user)
                .map(User::getDepartment)
                .ifPresent(department -> {
                    userHistory.setDepartment(user.getDepartment());
                });
        Optional.ofNullable(user)
                .map(User::getRole)
                .ifPresent(role -> {
                    userHistory.setRole(user.getRole());
                });
        Optional.ofNullable(user)
                .map(User::getCreatedByUser)
                .ifPresent(user1 -> {
                    userHistory.setCreatedByUser(user.getCreatedByUser());
                    userHistory.setCreatedByUser(user.getModifiedByUser());
                });
        userHistory.setDeleted(user.getDeleted());
        userHistory.setDescription(user.getDescription());
        userHistory.setStatus(user.getStatus());
        return userHistory;
    }

    public UserHistory mapModelToEntity(UserHistoryDTO userHistoryDTO){
        UserHistory userMapper = UserHistory.builder()
                .userName(userHistoryDTO.getUserName())
                .fullName(userHistoryDTO.getFullName())
                .firstName(userHistoryDTO.getFirstName())
                .lastName(userHistoryDTO.getLastName())
                .email(userHistoryDTO.getEmail())
                .phoneNumber(userHistoryDTO.getPhoneNumber())
                .build();

        userMapper.setDeleted(userHistoryDTO.getDeleted());
        userMapper.setDescription(userHistoryDTO.getDescription());
        userMapper.setStatus(userHistoryDTO.getStatus());
        return userMapper;
    }

    public UserHistory mapModelToEntity(UserDTO userDTO){
        UserHistory userMapper = UserHistory.builder()
                .userName(userDTO.getUserName())
                .fullName(userDTO.getFullName())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
        userMapper.setDeleted(userDTO.getDeleted());
        userMapper.setDescription(userDTO.getDescription());
        userMapper.setStatus(userDTO.getStatus());
        return userMapper;
    }

    public UserHistoryResponse mapResponseToEntity(UserHistory userHistory){
        UserHistoryResponse userResponse = UserHistoryResponse.builder()
                .id(userHistory.getId())
                .userName(userHistory.getUserName())
                .fullName(userHistory.getFullName())
                .email(userHistory.getEmail())
                .phoneNumber(userHistory.getPhoneNumber())
                .firstName(userHistory.getFirstName())
                .lastName(userHistory.getLastName())
                .build();
        Optional.ofNullable(userHistory)
                .map(UserHistory::getTitle)
                .ifPresent(title -> {
                    userResponse.setTitleResponse(titleMapper.mapResponseToEntity(userHistory.getTitle()));
                });
        Optional.ofNullable(userHistory)
                .map(UserHistory::getGroup)
                .ifPresent(group -> {
                    userResponse.setGroupResponse(groupMapper.mapResponseToEntity(userHistory.getGroup()));
                });
        Optional.ofNullable(userHistory)
                .map(UserHistory::getDepartment)
                .ifPresent(department -> {
                    userResponse.setDepartmentResponse(departmentMapper.mapResponseToEntity(userHistory.getDepartment()));
                });
        Optional.ofNullable(userHistory)
                .map(UserHistory::getRole)
                .ifPresent(role -> {
                    userResponse.setRoleResponse(roleMapper.mapResponseToEntity(userHistory.getRole()));
                });
        Optional.ofNullable(userHistory)
                .map(UserHistory::getCreatedByUser)
                .ifPresent(user1 -> {
                    userResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(userHistory.getCreatedByUser()));
                });
        Optional.ofNullable(userHistory)
                .map(UserHistory::getCreatedByUser)
                .ifPresent(user1 -> {
                    userResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(userHistory.getModifiedByUser()));
                });
        userResponse.setDeleted(userHistory.getDeleted());
        userResponse.setDescription(userHistory.getDescription());
        userResponse.setCreatedAt(userHistory.getCreatedAt());
        userResponse.setCreatedAt(userHistory.getCreatedAt());
        userResponse.setModifiedAt(userHistory.getModifiedAt());
        userResponse.setStatus(userHistory.getStatus());
        return  userResponse;
    }
}
