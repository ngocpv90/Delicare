package com.app.delicare.mappers.user;

import com.app.delicare.component.CommonUtils;
import com.app.delicare.dtos.user.UserContactDTO;
import com.app.delicare.entitys.users.User;
import com.app.delicare.entitys.users.UserContact;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.user.UserContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserContactMapper extends BaseMapper {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserContact mapModelToEntity(UserContactDTO userContactDTO){
        UserContact userContact = UserContact.builder()
                .phoneNumber(userContactDTO.getPhoneNumber())
                .build();
        if(!CommonUtils.isNullOrEmpty(userContactDTO.getUserId())){
            User user = userRepository.getReferenceById(userContactDTO.getUserId());
            userContact.setUser(user);
        }

        if(!CommonUtils.isNullOrEmpty(userContactDTO.getCreatedById())){
            User user = userRepository.getReferenceById(userContactDTO.getCreatedById());
            userContact.setCreatedByUser(user);
            userContact.setModifiedByUser(user);
        }
        userContact.setDeleted(userContactDTO.getDeleted());
        userContact.setDescription(userContactDTO.getDescription());
        userContact.setStatus(userContactDTO.getStatus());
        return userContact;
    }

    public UserContactResponse mapResponseToEntity(UserContact userContact){
        UserContactResponse userResponse = UserContactResponse.builder()
                .id(userContact.getId())
                .phoneNumber(userContact.getPhoneNumber())
                .build();
        Optional.ofNullable(userContact)
                .map(UserContact::getUser)
                .ifPresent(title -> {
                    userResponse.setUserResponse(userMapper.mapResponseToEntity(userContact.getUser()));
                });
        Optional.ofNullable(userContact)
                .map(UserContact::getCreatedByUser)
                .ifPresent(user1 -> {
                    userResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(userContact.getCreatedByUser()));
                });
        Optional.ofNullable(userContact)
                .map(UserContact::getCreatedByUser)
                .ifPresent(user1 -> {
                    userResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(userContact.getModifiedByUser()));
                });
        userResponse.setDeleted(userContact.getDeleted());
        userResponse.setDescription(userContact.getDescription());
        userResponse.setCreatedAt(userContact.getCreatedAt());
        userResponse.setCreatedAt(userContact.getCreatedAt());
        userResponse.setModifiedAt(userContact.getModifiedAt());
        userResponse.setStatus(userContact.getStatus());
        return  userResponse;
    }
}
