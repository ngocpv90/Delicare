package com.app.delicare.repositories;

import com.app.delicare.entitys.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserContactRepository extends JpaRepository<UserContact, Long>, JpaSpecificationExecutor<UserContact> {
    boolean existsByUserIdAndPhoneNumber(Long userId, String phoneNumber);
    Optional<UserContact> findByUserId(Long userId);
}
