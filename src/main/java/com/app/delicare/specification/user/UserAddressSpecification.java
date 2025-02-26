package com.app.delicare.specification.user;

import com.app.delicare.entitys.user.UserAddress;
import com.app.delicare.specification.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

public class UserAddressSpecification extends BaseSpecification<UserAddress> {
    public static Specification<UserAddress> hasUserId(Long userId, String condition) {
        return hasColumnCondition("user","id", userId, condition);
    }
//    public static Specification<UserAddress> isNotDeleted() {
//        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
//    }
//    public static Specification<UserAddress> hasDeleted(Long deletedId, String condition) {
//        return hasColumnCondition("deleted", deletedId, condition);
//    }
//    public static Specification<UserAddress> createdAfter(LocalDateTime createdAt, String condition) {
//        return hasColumnCondition("createdAt", createdAt, condition);
//    }
//    public static Specification<UserAddress> modifiedAfter(LocalDateTime modifiedAt, String condition) {
//        return hasColumnCondition("modifiedAt", modifiedAt, condition);
//    }
//    public static Specification<UserAddress> hasCreatedById(Long createdById, String condition) {
//        return hasColumnCondition("createdById", createdById, condition);
//    }
//    public static Specification<UserAddress> hasModifiedById(Long modifiedById, String condition) {
//        return hasColumnCondition("modifiedById", modifiedById, condition);
//    }
}
