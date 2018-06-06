package com.semantyca.nb.core.boundary;

import com.semantyca.nb.core.page.Page;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

@Path("pages")
@RequestScoped
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
            StreamSource xsltFile = new StreamSource();
            xsltFile.setInputStream(xmlPage.getXslt().openStream());
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

    class XMLPage {
        private String body;
        private URL xslt;

        XMLPage(Object object) throws JAXBException {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(object, sw);
            body = sw.toString();
            Page pageAnnotation = object.getClass().getAnnotation(Page.class);
            String fileName = XSLT_FOLDER + File.separator + pageAnnotation.value();
            ClassLoader classLoader = this.getClass().getClassLoader();
           // System.out.println(file.getAbsolutePath());
            xslt = classLoader.getResource(fileName);
        }

        public String getBody() {
            return body;
        }

        public URL getXslt() {
            return xslt;
        }

    }

}
