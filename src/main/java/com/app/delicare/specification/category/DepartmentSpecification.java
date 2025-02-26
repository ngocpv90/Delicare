package com.app.delicare.specification.category;

import com.app.delicare.entitys.category.Department;
import com.app.delicare.specification.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;


public class DepartmentSpecification extends BaseSpecification<Department> {

    public static Specification<Department> hasCompanyId(Long companyId, String condition) {
        return hasColumnCondition("company","id", companyId, condition);
    }
    public static Specification<Department> hasDepartmentCode(String departmentCode, String condition) {
        return hasColumnCondition("departmentCode", departmentCode, condition);
    }
    public static Specification<Department> hasDepartmentName(String departmentName, String condition) {
        return hasColumnCondition("departmentName", departmentName, condition);
    }

//    public static Specification<Department> isNotDeleted() {
//        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
//    }
//    public static Specification<Department> hasDeleted(Long deletedId, String condition) {
//        return hasColumnCondition("deleted", deletedId, condition);
//    }
//    public static Specification<Department> createdAfter(LocalDateTime createdAt, String condition) {
//        return hasColumnCondition("createdAt", createdAt, condition);
//    }
//    public static Specification<Department> modifiedAfter(LocalDateTime modifiedAt, String condition) {
//        return hasColumnCondition("modifiedAt", modifiedAt, condition);
//    }
//    public static Specification<Department> hasCreatedById(Long createdById, String condition) {
//        return hasColumnCondition("createdById", createdById, condition);
//    }
//    public static Specification<Department> hasModifiedById(Long modifiedById, String condition) {
//        return hasColumnCondition("modifiedById", modifiedById, condition);
//    }
}
