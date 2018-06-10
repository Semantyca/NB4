package com.semantyca.nb.core.page;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

@XmlRootElement(name = "page")
//@XmlType(propOrder={"id", "xslt"})
public class XMLPage {
    private static final String XSLT_FOLDER = "xslt";
    private String id;

    private String pageObject;
    private String xslt;

    public XMLPage(){}

    public XMLPage(String id, String xsl, String pageObject) {
        this.id = id;
        this.pageObject = pageObject;
        xslt = xsl;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    @XmlElement
    public String getXML(){
        try {
            Class objClass = Class.forName(pageObject);
            Constructor[] constructors = objClass.getDeclaredConstructors();
            JAXBContext context = JAXBContext.newInstance(objClass);
            Marshaller m = context.createMarshaller();
            StringWriter sw = new StringWriter();
            m.marshal(constructors[0].newInstance(), sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";


    }

    @XmlElement
    public String getXslt() {
      return xslt;
    }

    public URL getXsltPath(ServletContext context) {
        try {
            String realPath = context.getRealPath("/WEB-INF/classes");
            return Paths.get(realPath + File.separator + XSLT_FOLDER + File.separator + xslt).toUri().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
