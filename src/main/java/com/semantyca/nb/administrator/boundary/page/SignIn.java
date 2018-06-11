package com.semantyca.nb.administrator.boundary.page;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.page.Page;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "signin")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Page
public class SignIn {

    private String app = EnvConst.APP_ID;

    @XmlElement(name = "app")
    public String getApp() {
        return app;
    }

}