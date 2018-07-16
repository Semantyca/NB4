package com.semantyca.nb.core.dataengine.jpa.dto;

import com.semantyca.nb.core.dataengine.jpa.model.embedded.Reader;
import com.semantyca.nb.core.user.IUser;

import java.util.Map;
import java.util.function.Function;


public abstract class ReadingStateNormalizator<D,E> implements Function<D, E> {
    private IUser user;

    protected ReadingStateNormalizator(IUser user){
        this.user = user;
    }

    public boolean checkReadingState(Map<Long,Reader> readers){
        return checkReadingState(user, readers);
    }

    @Override
    public abstract E apply(D d);

    public static boolean checkReadingState(IUser user, Map<Long,Reader> readers){
        if (user.isSuperUser()){
            return true;
        }else {
            if (readers.size() > 0) {
                if (readers.get(user.getId()).isWasRead()) {
                    return true;
                }
            }
        }
        return false;
    }

}

