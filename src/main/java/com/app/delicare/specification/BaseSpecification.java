package com.app.delicare.specification;

import com.app.delicare.common.enums.ECommon;
import com.app.delicare.common.enums.ESpecification;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseSpecification<T> {
    /**
     * Phương thức áp dụng điều kiện cho một cột dựa trên tên cột và điều kiện cần kiểm tra.
     *
     * @param columnName Tên cột trong entity cần áp dụng điều kiện.
     * @param objectValue     Giá trị cần so sánh với cột.
     * @param condition Điều kiện so sánh (ví dụ: "equal", "greaterThan", "lessThan", "like").
     * @return Predicate đại diện cho điều kiện truy vấn.
     */
    public static <T> Specification<T> hasColumnCondition(String columnName, Object objectValue, String condition) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (objectValue == null) {
                return null;  // Không có điều kiện nếu giá trị là null
            }

            switch (condition) {

                case "notEqual":
                    return builder.notEqual(root.get(columnName), objectValue);

                case "greaterThan":
                    return builder.greaterThan(root.get(columnName), (Comparable) objectValue);

                case "greaterThanOrEqualTo":
                    return builder.greaterThanOrEqualTo(root.get(columnName), (Comparable) objectValue);

                case "lessThan":
                    return builder.lessThan(root.get(columnName), (Comparable) objectValue);

                case "lessThanOrEqualTo":
                    return builder.lessThanOrEqualTo(root.get(columnName), (Comparable) objectValue);

                case "like":
                    if (objectValue instanceof String) {
                        return builder.like(root.get(columnName), "%" + objectValue + "%");
                    }
                    return null;
                case "notLike":
                    if (objectValue instanceof String) {
                        return builder.notLike(root.get(columnName), "%" + objectValue + "%");
                    }
                    return null;
                default:
                    return builder.equal(root.get(columnName), objectValue);
            }
        };
    }

    /**
     * Phương thức áp dụng điều kiện cho một cột dựa trên tên cột và điều kiện cần kiểm tra.
     *
     * @param manyToOneName Tên JoinColumn trong entity cần áp dụng điều kiện.
     * @param columnName Tên cột trong entity cần áp dụng điều kiện.
     * @param objectValue     Giá trị cần so sánh với cột.
     * @param condition Điều kiện so sánh (ví dụ: "equal", "greaterThan", "lessThan", "like").
     * @return Predicate đại diện cho điều kiện truy vấn.
     */
    public static <T> Specification<T> hasColumnCondition(String manyToOneName, String columnName, Object objectValue, String condition) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (objectValue == null) {
                return null;
            }

            switch (condition) {
                case "notEqual":
                    return builder.notEqual(root.get(manyToOneName).get(columnName), objectValue);

                case "greaterThan":
                    return builder.greaterThan(root.get(manyToOneName).get(columnName), (Comparable) objectValue);

                case "greaterThanOrEqualTo":
                    return builder.greaterThanOrEqualTo(root.get(manyToOneName).get(columnName), (Comparable) objectValue);

                case "lessThan":
                    return builder.lessThan(root.get(manyToOneName).get(columnName), (Comparable) objectValue);

                case "lessThanOrEqualTo":
                    return builder.lessThanOrEqualTo(root.get(manyToOneName).get(columnName), (Comparable) objectValue);

                case "like":
                    if (objectValue instanceof String) {
                        return builder.like(root.get(manyToOneName).get(columnName), "%" + objectValue + "%");
                    }
                    throw new IllegalArgumentException("Value must be of type String for 'like' condition");
                case "notLike":
                    if (objectValue instanceof String) {
                        return builder.notLike(root.get(manyToOneName).get(columnName), "%" + objectValue + "%");
                    }
                    throw new IllegalArgumentException("Value must be of type String for 'like' condition");
                default:
                    return builder.equal(root.get(manyToOneName).get(columnName), objectValue);
            }
        };
    }

    public static <T> Specification<T> isNotDeleted() {
        return hasColumnCondition("deleted", ECommon.DELETED_STATUS.ACTIVE.getValue(), ESpecification.EQUAL.getValue());
    }
    public static <T> Specification<T> hasDeleted(Long deletedId, String condition) {
        return hasColumnCondition("deleted", deletedId, condition);
    }
    public static <T> Specification<T> createdAfter(LocalDateTime createdAt, String condition) {
        return hasColumnCondition("createdAt", createdAt, condition);
    }
    public static <T> Specification<T> modifiedAfter(LocalDateTime modifiedAt, String condition) {
        return hasColumnCondition("modifiedAt", modifiedAt, condition);
    }
    public static <T> Specification<T> hasCreatedById(Long createdById, String condition) {
        return hasColumnCondition("createdById", createdById, condition);
    }
    public static <T> Specification<T> hasModifiedById(Long modifiedById, String condition) {
        return hasColumnCondition("modifiedById", modifiedById, condition);
    }
}
