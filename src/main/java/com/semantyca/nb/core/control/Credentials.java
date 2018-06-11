package com.semantyca.nb.core.control;

public class Credentials {
    private String userName;
    private String pwd;

    public Credentials(String userName, String password) {
        this.userName = userName;
        pwd = password;
    }

    public Credentials() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
