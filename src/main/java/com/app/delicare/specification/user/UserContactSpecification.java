package com.app.delicare.specification.user;

import com.app.delicare.entitys.user.UserContact;
import com.app.delicare.specification.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

public class UserContactSpecification extends BaseSpecification<UserContact> {
    public static Specification<UserContact> hasUserId(Long userId, String condition) {
        return hasColumnCondition("user","id", userId, condition);
    }
//    public static Specification<UserContact> isNotDeleted() {
//        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
//    }
//    public static Specification<UserContact> hasDeleted(Long deletedId, String condition) {
//        return hasColumnCondition("deleted", deletedId, condition);
//    }
//    public static Specification<UserContact> createdAfter(LocalDateTime createdAt, String condition) {
//        return hasColumnCondition("createdAt", createdAt, condition);
//    }
//    public static Specification<UserContact> modifiedAfter(LocalDateTime modifiedAt, String condition) {
//        return hasColumnCondition("modifiedAt", modifiedAt, condition);
//    }
//    public static Specification<UserContact> hasCreatedById(Long createdById, String condition) {
//        return hasColumnCondition("createdById", createdById, condition);
//    }
//    public static Specification<UserContact> hasModifiedById(Long modifiedById, String condition) {
//        return hasColumnCondition("modifiedById", modifiedById, condition);
//    }
}
