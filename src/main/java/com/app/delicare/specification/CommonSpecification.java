package com.app.delicare.specification;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class CommonSpecification {
    public static <T> Specification<T> isNotDeleted() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(root.get("deleted"), 0);  // 0 có thể là chưa xóa
        };
    }
    public static <T> Specification<T> createdAfter(LocalDateTime createdAt) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.greaterThanOrEqualTo(root.get("createdAt"), createdAt);
        };
    }
    public static <T> Specification<T> modifiedAfter(LocalDateTime modifiedAt) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.greaterThanOrEqualTo(root.get("modifiedAt"), modifiedAt);
        };
    }
    public static <T> Specification<T> hasCreatedById(Long createdById) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(root.get("createdById"), createdById);
        };
    }

    // Specification lọc theo modifiedById
    public static <T> Specification<T> hasModifiedById(Long modifiedById) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(root.get("modifiedById"), modifiedById);
        };
    }
}
