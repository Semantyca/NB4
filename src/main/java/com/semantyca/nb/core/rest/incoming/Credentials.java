package com.semantyca.nb.core.rest.incoming;

import java.io.Serializable;

public class Credentials implements Serializable {
    private static final long serialVersionUID = 1L;
    private String login;
    private String pwd;
    private String hash;

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
