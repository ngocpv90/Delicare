package com.app.delicare.specification;

import com.app.delicare.common.enums.ECommon;
import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.user.User;
import com.app.delicare.specification.base.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class UserSpecification extends BaseSpecification<User> {

    public static Specification<User> hasUserName(String userName, String condition) {
        return hasColumnCondition("userName", userName, condition);
    }
    public static Specification<User> hasFullName(String fullName, String condition) {
        return hasColumnCondition("fullName", fullName, condition);
    }
    public static Specification<User> hasPhoneNumber(String phoneNumber, String condition) {
        return hasColumnCondition("phoneNumber", phoneNumber, condition);
    }
    public static Specification<User> hasEmail(String email, String condition) {
        return hasColumnCondition("email", email, condition);
    }
    public static Specification<User> hasGroupId(Long groupId, String condition) {
        return hasColumnCondition("group","id", groupId, condition);
    }
    public static Specification<User> hasTitleId(Long titleId, String condition) {
        return hasColumnCondition("title","id", titleId, condition);
    }
    public static Specification<User> hasDepartmentId(Long departmentId, String condition) {
        return hasColumnCondition("department","id", departmentId, condition);
    }
    public static Specification<User> isNotDeleted() {
        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
    }
    public static Specification<User> hasDeleted(Long deletedId, String condition) {
        return hasColumnCondition("deleted", deletedId, condition);
    }
    public static Specification<User> createdAfter(LocalDateTime createdAt, String condition) {
        return hasColumnCondition("createdAt", createdAt, condition);
    }
    public static Specification<User> modifiedAfter(LocalDateTime modifiedAt, String condition) {
        return hasColumnCondition("modifiedAt", modifiedAt, condition);
    }
    public static Specification<User> hasCreatedById(Long createdById, String condition) {
        return hasColumnCondition("createdById", createdById, condition);
    }
    public static Specification<User> hasModifiedById(Long modifiedById, String condition) {
        return hasColumnCondition("modifiedById", modifiedById, condition);
    }
}
