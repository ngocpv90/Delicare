package com.app.delicare.repositories;

import com.app.delicare.entitys.UserContact;
import com.app.delicare.entitys.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Optional<UserContact> findByUserId(Long userId);
}
