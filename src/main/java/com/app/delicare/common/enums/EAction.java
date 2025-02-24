package com.app.delicare.common.enums;

public enum EAction {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    READ("READ"),
    DELETE("DELETE"),
    EXPORT("EXPORT"),
    APPROVE("APPROVE"),
    ASSIGN("ASSIGN"),
    IMPORT("IMPORT"),
    REJECT("REJECT")
    ;

    private final String value;
    EAction(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
