package com.app.delicare.specification;

import com.app.delicare.common.enums.ECommon;
import com.app.delicare.common.enums.ESpecification;
import com.app.delicare.entitys.category.Title;
import com.app.delicare.specification.base.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TitleSpecification extends BaseSpecification<Title> {
    public static Specification<Title> isNotDeleted() {
        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
    }
    public static Specification<Title> hasDeleted(Long deletedId, String condition) {
        return hasColumnCondition("deleted", deletedId, condition);
    }
    public static Specification<Title> createdAfter(LocalDateTime createdAt, String condition) {
        return hasColumnCondition("createdAt", createdAt, condition);
    }
    public static Specification<Title> modifiedAfter(LocalDateTime modifiedAt, String condition) {
        return hasColumnCondition("modifiedAt", modifiedAt, condition);
    }
    public static Specification<Title> hasCreatedById(Long createdById, String condition) {
        return hasColumnCondition("createdById", createdById, condition);
    }
    public static Specification<Title> hasModifiedById(Long modifiedById, String condition) {
        return hasColumnCondition("modifiedById", modifiedById, condition);
    }
}
