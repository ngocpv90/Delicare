package com.app.delicare.common.enums;

public enum ESpecification {
    EQUAL("equal"),
    NOT_EQUAL("notEqual"),
    GREATER_THAN_OR_EQUAL_TO("greaterThanOrEqualTo"),
    LESS_THAN_OR_EQUAL_TO("lessThanOrEqualTo"),
    LIKE("like"),
    NOT_LIKE("notLike"),
    BETWEEN("between"),
    GREATER_THAN("greaterThan"),
    LESS_THAN("lessThan"),
    ;

    private final String value;

    ESpecification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
