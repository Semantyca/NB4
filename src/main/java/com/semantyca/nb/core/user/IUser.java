package com.semantyca.nb.core.user;

public interface IUser {

    Long getId();

    default boolean  isSuperUser(){
        return false;
    }


}
