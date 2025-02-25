package com.app.delicare.service.implement;

import com.app.delicare.dtos.user.UserContactDTO;
import com.app.delicare.responses.user.UserContactResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserContactService {
    UserContactResponse createUserConact(UserContactDTO userContactDTO);
    List<UserContactResponse> getAllUserConact();
    Page<UserContactResponse> getPageUserConact(PageRequest pageRequest);
    UserContactResponse getUserConactById(Long id);
    List<UserContactResponse> getUserContactByUserId(Long userId);
    UserContactResponse updateUserConact(Long id, UserContactDTO userContactDTO);
    boolean existsByUserIdAndPhoneNumber(Long userId, String phoneNumber);
}
