package com.app.delicare.service.user;

import com.app.delicare.dtos.user.UserHistoryDTO;
import com.app.delicare.entitys.user.User;
import com.app.delicare.entitys.user.UserHistory;
import com.app.delicare.mappers.user.UserHistoryMapper;
import com.app.delicare.repositories.user.UserHistoryRepository;
import com.app.delicare.responses.user.UserHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHistoryService implements IUserHistoryService {
    private final UserHistoryRepository userHistoryRepository;
    private final UserHistoryMapper userHistoryMapper;

    @Override
    public UserHistoryResponse createUserHistory(UserHistoryDTO userHistoryDTO) {
        return null;
    }

    @Override
    public UserHistoryResponse createUserHistory(User user) {
        try{
            UserHistory userHistory = userHistoryMapper.mapEntityToEntity(user);
            userHistoryRepository.save(userHistory);
            userHistoryRepository.flush();
            return userHistoryMapper.mapResponseToEntity(userHistory);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserHistory> getAllUserHistory() {
        return List.of();
    }

    @Override
    public Page<UserHistoryResponse> getAllUserHistory(PageRequest pageRequest) {
        return null;
    }

    @Override
    public UserHistoryResponse getUserHistoryById(Long id) {
        return null;
    }

    @Override
    public void updateUserHistory(Long userId, UserHistoryDTO userHistoryDTO) {

    }

    @Override
    public void updateUserHistory(Long id) {

    }
}
