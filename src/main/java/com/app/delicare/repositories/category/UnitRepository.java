package com.app.delicare.repositories.category;

import com.app.delicare.entitys.category.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UnitRepository extends JpaRepository<Units, Long>, JpaSpecificationExecutor<Units> {
}
