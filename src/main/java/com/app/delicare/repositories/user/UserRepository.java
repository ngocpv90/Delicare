package com.app.delicare.repositories.user;

import com.app.delicare.entitys.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
