package com.app.delicare.repositories;

import com.app.delicare.entitys.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UnitRepository extends JpaRepository<Units, Long>, JpaSpecificationExecutor<Units> {
}
