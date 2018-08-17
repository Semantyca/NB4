package com.semantyca.officeframe.modules.organizations.model.constants;

/**
 * https://en.wikipedia.org/wiki/ISO_3166-2
 *
 * @author Kayra created 27-12-2015
 */
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
