package com.semantyca.officeframe.boundary.page;


import com.semantyca.nb.core.page.Page;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "workspace")
@XmlAccessorType(XmlAccessType.FIELD)
@Page("workspace.xsl")
public class Workspace {

    @XmlElement(name = "logo")
    public String logo = "no";

}
