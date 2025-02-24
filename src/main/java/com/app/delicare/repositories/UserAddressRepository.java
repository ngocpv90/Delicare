package com.app.delicare.repositories;

import com.app.delicare.entitys.UserAddress;
import com.app.delicare.entitys.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long>, JpaSpecificationExecutor<UserAddress> {
    Optional<UserContact> findByUserId(Long userId);
}
