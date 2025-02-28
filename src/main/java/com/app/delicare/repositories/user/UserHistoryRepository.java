package com.app.delicare.repositories.user;

import com.app.delicare.entitys.users.UserContact;
import com.app.delicare.entitys.users.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Optional<UserContact> findByUserId(Long userId);
}
