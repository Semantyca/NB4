package com.semantyca.nb.core.rest.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.user.AnonymousUser;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.modules.administrator.dao.UserDAO;
import com.semantyca.nb.util.StringUtil;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import java.io.File;
import java.io.Serializable;

public class Session implements Serializable {
    private IUser user = new AnonymousUser();
    private String token;
    private LanguageCode lang = EnvConst.getDefaultLang();
    private int pageSize = EnvConst.DEFAULT_PAGE_SIZE;
    private String redirectURL = "explorations";
    private boolean isAuthenticated;

    public Session() {

    }

    public Session(IUser user) {
        this.user = user;
    }

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

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @JsonIgnore
    public File getTmpDir() {
        File userTmpDir = new File(EnvConst.TMP_DIR + File.separator + EnvConst.SERVER_NAME + File.separator + user.getId());
        if (!userTmpDir.exists()) {
            userTmpDir.mkdirs();
        }
        return userTmpDir;
    }

    public void authenticate(String login, String pwd) {

        if (login != null && pwd != null) {
            Instance<UserDAO> instance;
            try {
                instance = CDI.current().select(UserDAO.class);
                UserDAO uDao = instance.get();

                IUser user = uDao.findByLogin(login);

                if (user != null) {
                    String pwdHash = StringUtil.encode(pwd);
                    if ((user.getPasswordhash() != null && user.getPasswordhash().equals(pwdHash)) || user.getPassword() != null && user.getPassword().equals(pwd)) {
                        isAuthenticated = true;
                        this.user = user;
                        return;
                    }
                } else {
                    Lg.warning("\"" + login + "\" user not found");
                }
            } finally {
            /*    if (instance != null)
                CDI.current().destroy(instance);*/
            }
        } else {
            Lg.error("login or password is null");
        }
    }
}
