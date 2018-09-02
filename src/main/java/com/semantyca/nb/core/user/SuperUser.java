package com.semantyca.nb.core.user;

public class SuperUser extends SystemUser {
    public final static long ID = -1;
    public static String USER_NAME = "supervisor";

    public String getUserName() {
        return USER_NAME;
    }

    public boolean isAllowed(String app) {
        return true;
    }

    @Override
    public Long getId() {
        return ID;
    }

    @Override
    public boolean isSuperUser() {
        return true;
    }


    @Override
    public String getLogin() {
        return USER_NAME;
    }

}
