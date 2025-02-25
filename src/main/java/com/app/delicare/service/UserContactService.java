package com.app.delicare.service;

import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.dtos.user.UserContactDTO;
import com.app.delicare.entitys.user.User;
import com.app.delicare.entitys.user.UserContact;
import com.app.delicare.mappers.UserContactMapper;
import com.app.delicare.repositories.user.UserContactRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.user.UserContactResponse;
import com.app.delicare.service.implement.IUserContactService;
import com.app.delicare.specification.UserContactSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserContactService implements IUserContactService {
    private final UserContactRepository userContactRepository;
    private final UserRepository userRepository;
    private final UserContactMapper userContactMapper;

    @Override
    public UserContactResponse createUserConact(UserContactDTO userContactDTO) {
        try{
            UserContact userContact = userContactMapper.mapModelToEntity(userContactDTO);
            userContactRepository.save(userContact);
            userContactRepository.flush();
            return userContactMapper.mapResponseToEntity(userContact);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserContactResponse> getAllUserConact() {
        Specification<UserContact> specification = Specification.where(UserContactSpecification.isNotDeleted());
        return userContactRepository.findAll(specification).stream().map(userContact -> {
                    UserContactResponse userContactResponse = userContactMapper.mapResponseToEntity(userContact);
                    return userContactResponse;
                })
                .toList();
    }

    @Override
    public Page<UserContactResponse> getPageUserConact(PageRequest pageRequest) {
        Specification<UserContact> specification = Specification.where(UserContactSpecification.isNotDeleted());
        return userContactRepository.findAll(specification, pageRequest).map(userContact -> {
            return userContactMapper.mapResponseToEntity(userContact);
        });
    }

    @Override
    public UserContactResponse getUserConactById(Long id) {
        UserContact userContact = userContactRepository.getReferenceById(id);
        return userContactMapper.mapResponseToEntity(userContact);
    }

    @Override
    public List<UserContactResponse> getUserContactByUserId(Long userId) {
        Specification<UserContact> specification = Specification.where(UserContactSpecification.isNotDeleted())
                .and(UserContactSpecification.hasUserId(userId, ESpecification.EQUAL.getValue()));
        return userContactRepository.findAll(specification).stream().map(userContact -> {
            return userContactMapper.mapResponseToEntity(userContact);
        }).toList();
    }

    @Override
    public UserContactResponse updateUserConact(Long id, UserContactDTO userContactDTO) {
        User userModified = userRepository.getReferenceById(userContactDTO.getModifiedById());
        UserContact userUpdate = userContactMapper.mapModelToEntity(userContactDTO);
        userUpdate.setId(id);
        userUpdate.setModifiedByUser(userModified);
        userContactRepository.save(userUpdate);
        return userContactMapper.mapResponseToEntity(userUpdate);
    }

    @Override
    public boolean existsByUserIdAndPhoneNumber(Long userId, String phoneNumber) {
        return userContactRepository.existsByUserIdAndPhoneNumber(userId, phoneNumber);
    }
}
