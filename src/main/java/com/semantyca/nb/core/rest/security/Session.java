package com.semantyca.nb.core.rest.security;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.model.User;

import java.io.Serializable;

public class Session implements Serializable {
    private IUser user;
    private String token;
    private LanguageCode lang = EnvConst.getDefaultLang();
    private int pageSize = EnvConst.DEFAULT_PAGE_SIZE;
    private String redirectURL = "";

    public LanguageCode getLang() {
        return lang;
    }

    public void setLang(LanguageCode lang) {
        this.lang = lang;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public User getRealUser() {
        try {
            return (User) user;
        }catch(ClassCastException e){
            return null;
        }
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
