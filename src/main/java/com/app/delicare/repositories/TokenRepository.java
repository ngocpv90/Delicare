package com.app.delicare.repositories;

import com.app.delicare.entitys.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
