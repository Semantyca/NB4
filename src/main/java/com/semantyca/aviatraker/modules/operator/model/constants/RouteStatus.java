package com.semantyca.aviatraker.modules.operator.model.constants;

public enum RouteStatus {
    UNKNOWN(0), PLANNED(71), DONE(72);

    private int code;

    RouteStatus(int code) {
        this.code = code;
    }

    public static RouteStatus getType(int code) {
        for (RouteStatus type : values()) {
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
