package com.semantyca.officeframe.modules.organizations.model.constants;

public enum RoleType {
    UNKNOWN(0), CUSTOM(45), FIRED(46);

    private int code;

    RoleType(int code) {
        this.code = code;
    }

    public static RoleType getType(int code) {
        for (RoleType type : values()) {
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
