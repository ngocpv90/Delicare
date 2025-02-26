package com.app.delicare.repositories.category;

import com.app.delicare.entitys.category.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FunctionRepository extends JpaRepository<Function, Long>,
        JpaSpecificationExecutor<Function> {
}
