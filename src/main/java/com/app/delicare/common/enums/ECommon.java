package com.app.delicare.common.enums;

public class ECommon {
    public enum ACTIVE_STATUS {
        ACTIVE(1L, "active"),
        IN_ACTIVE(0L, "in.active");

        private final Long value;
        private final String name;

        ACTIVE_STATUS(Long value, String name) {
            this.value = value;
            this.name = name;
        }

        public Long getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
    public enum DELETED_STATUS {
        ACTIVE(0L, "active"),
        IN_ACTIVE(1L, "in.active");

        private final Long value;
        private final String name;

        DELETED_STATUS(Long value, String name) {
            this.value = value;
            this.name = name;
        }

        public Long getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
