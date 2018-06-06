package com.semantyca.essential.boundary;

import com.semantyca.essential.page.Page;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@Path("pages")
public class PagesResource {
    private static final String XSLT_FOLDER = "xslt";

    @Context
    HttpServletRequest servletContext;
        
    @Inject
    PageProvider provider;

    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getDefault() {
        return getPage("signin");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response getPage(@PathParam("id") String id) {
        Object pageObject = getXML(id).getEntity();
        StringWriter writer = new StringWriter();
        try {
            XMLPage xmlPage = new XMLPage(pageObject);
            Source source = new StreamSource(new StringReader(xmlPage.getBody()));
            URL url = getClass().getClassLoader().getResource(xmlPage.getXslt());
            StreamSource xsltFile = new StreamSource();
            xsltFile.setInputStream(url.openStream());
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(xsltFile);
            transformer.transform(source, new StreamResult(writer));
        } catch (TransformerConfigurationException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
        } catch (TransformerException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
        } catch (JAXBException e) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(200).entity(writer.toString()).build();
    }

    @GET
    @Path("xml/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getXML(@PathParam("id") String id) {
          return Response.status(200).entity(provider.getPage(id)).build();
    }

    class XMLPage{
        private String body;
        private String xslt;

        XMLPage(Object object) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(object, sw);
            body = sw.toString();
 //           Page pageAnnotation = object.getClass().getAnnotation(Page.class);
              //String realPath = servletContext.getContextPath();
                String fileName = XSLT_FOLDER + File.separator +  "signin.xsl";
                ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
              System.out.println(file.getAbsolutePath());
            xslt = XSLT_FOLDER + File.separator +  "signin.xsl";
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getXslt() {
            return xslt;
        }

        public void setXslt(String xslt) {
            this.xslt = xslt;
        }

    }

}
