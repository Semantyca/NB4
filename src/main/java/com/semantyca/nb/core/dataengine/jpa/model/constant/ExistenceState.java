package com.semantyca.nb.core.dataengine.jpa.model.constant;

public enum ExistenceState {
    UNKNOWN(0), NOT_SAVED(1), SAVED(2), DELETED(3);

    private int code;

    ExistenceState(int code) {
        this.code = code;
    }

    public static ExistenceState getType(int code) {
        for (ExistenceState type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return code;
    }

}
