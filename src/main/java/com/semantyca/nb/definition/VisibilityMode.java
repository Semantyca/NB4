package com.semantyca.nb.definition;

public enum VisibilityMode {
    UNKNOWN(0), NORMAL(102), HIDDEN(103);

    private int code;

    VisibilityMode(int code) {
        this.code = code;
    }

    public static VisibilityMode getType(int code) {
        for (VisibilityMode type : values()) {
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

