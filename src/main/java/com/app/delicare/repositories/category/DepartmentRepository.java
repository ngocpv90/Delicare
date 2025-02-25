package com.app.delicare.repositories.category;

import com.app.delicare.entitys.category.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    Department findByDepartmentCode(String departmentCode);
}
