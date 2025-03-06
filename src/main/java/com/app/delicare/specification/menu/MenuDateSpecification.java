package com.app.delicare.specification.menu;

import com.app.delicare.entitys.menu.MenuDate;
import com.app.delicare.specification.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class MenuDateSpecification extends BaseSpecification<MenuDate> {
    public static Specification<MenuDate> hasMenuId(Long menuId, String condition) {
        return hasColumnCondition("menu","id", menuId, condition);
    }
    public static Specification<MenuDate> hasMenuDate(Date menuDate, String condition) {
        return hasColumnCondition("menuDate", menuDate, condition);
    }
    public static Specification<MenuDate> hasMenuWeek(Long menuWeek, String condition) {
        return hasColumnCondition("menuWeek", menuWeek, condition);
    }
    public static Specification<MenuDate> hasMenuMonth(Long menuMonth, String condition) {
        return hasColumnCondition("menuMonth", menuMonth, condition);
    }
    public static Specification<MenuDate> hasMenuYear(Long menuYear, String condition) {
        return hasColumnCondition("menuYear", menuYear, condition);
    }
//    public static Specification<MenuDay> isNotDeleted() {
//        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
//    }
//    public static Specification<MenuDay> hasDeleted(Long deletedId, String condition) {
//        return hasColumnCondition("deleted", deletedId, condition);
//    }
//    public static Specification<MenuDay> createdAfter(LocalDateTime createdAt, String condition) {
//        return hasColumnCondition("createdAt", createdAt, condition);
//    }
//    public static Specification<MenuDay> modifiedAfter(LocalDateTime modifiedAt, String condition) {
//        return hasColumnCondition("modifiedAt", modifiedAt, condition);
//    }
//    public static Specification<MenuDay> hasCreatedById(Long createdById, String condition) {
//        return hasColumnCondition("createdById", createdById, condition);
//    }
//    public static Specification<MenuDay> hasModifiedById(Long modifiedById, String condition) {
//        return hasColumnCondition("modifiedById", modifiedById, condition);
//    }
}
