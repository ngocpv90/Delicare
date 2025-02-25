package com.app.delicare.specification;

import com.app.delicare.common.enums.ECommon;
import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.menu.Menu;
import com.app.delicare.specification.base.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class MenuSpecification extends BaseSpecification<Menu> {
    public static Specification<Menu> isNotDeleted() {
        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
    }
    public static Specification<Menu> hasDeleted(Long deletedId, String condition) {
        return hasColumnCondition("deleted", deletedId, condition);
    }
    public static Specification<Menu> createdAfter(LocalDateTime createdAt, String condition) {
        return hasColumnCondition("createdAt", createdAt, condition);
    }
    public static Specification<Menu> modifiedAfter(LocalDateTime modifiedAt, String condition) {
        return hasColumnCondition("modifiedAt", modifiedAt, condition);
    }
    public static Specification<Menu> hasCreatedById(Long createdById, String condition) {
        return hasColumnCondition("createdById", createdById, condition);
    }
    public static Specification<Menu> hasModifiedById(Long modifiedById, String condition) {
        return hasColumnCondition("modifiedById", modifiedById, condition);
    }
}
