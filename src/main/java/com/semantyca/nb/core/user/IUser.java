package com.semantyca.nb.core.user;

import com.semantyca.nb.core.user.constants.UserStatusCode;

public interface IUser {

    Long getId();

    default boolean  isSuperUser(){
        return false;
    }

    String getPassword();

    String getPasswordhash();

    UserStatusCode getStatus();

    String getLogin();
}
