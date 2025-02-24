package com.app.delicare.service.implement;

import com.app.delicare.dtos.UserDTO;
import com.app.delicare.entitys.User;
import com.app.delicare.entitys.UserHistory;
import com.app.delicare.dtos.UserHistoryDTO;
import com.app.delicare.responses.UserHistoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserHistoryService {
    UserHistoryResponse createUserHistory(UserHistoryDTO userHistoryDTO);
    UserHistoryResponse createUserHistory(User user);
    List<UserHistory> getAllUserHistory();
    Page<UserHistoryResponse> getAllUserHistory(PageRequest pageRequest);
    UserHistoryResponse getUserHistoryById(Long id);
    void updateUserHistory(Long userId, UserHistoryDTO userHistoryDTO);
    void updateUserHistory(Long id);
}
