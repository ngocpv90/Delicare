package com.app.delicare.specification;

import com.app.delicare.common.enums.ECommon;
import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.category.Group;
import com.app.delicare.specification.base.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class GroupSpecification extends BaseSpecification<Group> {
    public static Specification<Group> hasDepartmentId(Long departmentId, String condition) {
        return hasColumnCondition("department","id", departmentId, condition);
    }
    public static Specification<Group> hasGroupCode(String groupCode, String condition) {
        return hasColumnCondition("groupCode", groupCode, condition);
    }
    public static Specification<Group> hasGroupName(String groupName, String condition) {
        return hasColumnCondition("groupName", groupName, condition);
    }

    public static Specification<Group> isNotDeleted() {
        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
    }
    public static Specification<Group> hasDeleted(Long deletedId, String condition) {
        return hasColumnCondition("deleted", deletedId, condition);
    }
    public static Specification<Group> createdAfter(LocalDateTime createdAt, String condition) {
        return hasColumnCondition("createdAt", createdAt, condition);
    }
    public static Specification<Group> modifiedAfter(LocalDateTime modifiedAt, String condition) {
        return hasColumnCondition("modifiedAt", modifiedAt, condition);
    }
    public static Specification<Group> hasCreatedById(Long createdById, String condition) {
        return hasColumnCondition("createdById", createdById, condition);
    }
    public static Specification<Group> hasModifiedById(Long modifiedById, String condition) {
        return hasColumnCondition("modifiedById", modifiedById, condition);
    }
}
