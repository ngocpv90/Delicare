package com.app.delicare.repositories.user;

import com.app.delicare.entitys.user.UserAddress;
import com.app.delicare.entitys.user.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {
    Optional<UserContact> findByUserId(Long userId);
}
