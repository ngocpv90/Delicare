package com.app.delicare.repositories.user;

import com.app.delicare.entitys.user.UserContact;
import com.app.delicare.entitys.user.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Optional<UserContact> findByUserId(Long userId);
}
