package com.semantyca.nb.core.boundary;


import com.semantyca.nb.core.page.XMLPage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Path("pages")
@RequestScoped
public class PagesResource {

    @Context
    ServletContext servletContext;

    @Inject
    PageProvider provider;

    @GET
    @Produces({MediaType.TEXT_HTML})
    public Response getDefault() {
        return getPage("index");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response getPage(@PathParam("id") String id) {
        StringWriter writer = new StringWriter();
        try {
            XMLPage xmlPage = provider.getPage(id);
            Source source = new StreamSource(new StringReader(xmlPage.getXML()));
            StreamSource xsltFile = new StreamSource();
            xsltFile.setInputStream(xmlPage.getXsltPath(servletContext).openStream());
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(xsltFile);
            transformer.transform(source, new StreamResult(writer));
        } catch (TransformerConfigurationException var9) {
            return Response.status(Status.BAD_REQUEST).type("application/json").build();
        } catch (TransformerException var10) {
            return Response.status(Status.BAD_REQUEST).type("application/json").build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

        return Response.status(200).entity(writer.toString()).build();
    }

    @GET
    @Path("xml/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getXML(@PathParam("id") String id) {
        return Response.status(200).entity(provider.getPage(id).getXML()).build();
    }

}
