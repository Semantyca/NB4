package com.semantyca.administrator.boundary.page;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.page.Page;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "signin")
@XmlAccessorType(XmlAccessType.FIELD)
@Page("signin.xsl")
public class SignIn {
   public String app = EnvConst.APP_ID;

}