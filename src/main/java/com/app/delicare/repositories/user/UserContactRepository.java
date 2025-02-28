package com.app.delicare.repositories.user;

import com.app.delicare.entitys.users.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserContactRepository extends JpaRepository<UserContact, Long>, JpaSpecificationExecutor<UserContact> {
    boolean existsByUserIdAndPhoneNumber(Long userId, String phoneNumber);
    Optional<UserContact> findByUserId(Long userId);
}
