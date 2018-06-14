package com.semantyca.nb.core.user;

public class AnonymousUser extends SystemUser {
    public final static String USER_NAME = "anonymous";
    public final static long ID = 0;

    public String getUserID() {
        return USER_NAME;
    }

    public String getUserName() {
        return USER_NAME;
    }

    public Long getId() {
        return (long) ID;
    }

    public String getLogin() {
        return USER_NAME;
    }

    public boolean isAnonymous(){
        return true;
    }

}
