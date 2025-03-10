package com.app.delicare.service.user;

import com.app.delicare.dtos.user.UserDTO;
import com.app.delicare.responses.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserDTO userDTO);
    List<UserResponse> getAllUser();
    Page<UserResponse> getPageUser(PageRequest pageRequest);
    UserResponse getUserById(Long id);
    UserResponse getUserByUserName(String userName);
    UserResponse updateUser(Long userId, UserDTO userDTO);

    String login(String userName, String password) throws Exception;
    String verifyToken(String token) throws Exception;
    boolean existsByUserName(String userName);
}
