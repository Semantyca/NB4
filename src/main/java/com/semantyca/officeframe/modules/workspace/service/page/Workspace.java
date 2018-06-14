package com.semantyca.officeframe.modules.workspace.service.page;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "workspace")
@XmlAccessorType(XmlAccessType.FIELD)
public class Workspace {

    @XmlElement(name = "logo")
    public String logo = "no";

}
