package com.semantyca.nb.core.rest.security;

import com.semantyca.nb.administrator.entity.User;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.localization.constants.LanguageCode;

public class UserSession {
    private User user;
    private String token;
    private LanguageCode lang = EnvConst.getDefaultLang();
    private int pageSize = EnvConst.DEFAULT_PAGE_SIZE;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
