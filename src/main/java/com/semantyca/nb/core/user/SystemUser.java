package com.semantyca.nb.core.user;

import com.semantyca.nb.core.user.constants.UserStatusCode;

public abstract class SystemUser implements IUser {

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getPasswordhash() {
        return "";
    }


    @Override
    public UserStatusCode getStatus() {
        return UserStatusCode.REGISTERED;
    }


}
