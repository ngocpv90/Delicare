package com.app.delicare.repositories.category;

import com.app.delicare.entitys.category.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActionRepository extends JpaRepository<Action, Long>,
        JpaSpecificationExecutor<Action> {
}
