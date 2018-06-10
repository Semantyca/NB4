package com.semantyca.nb.core.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class XSLTNotFoundMapper  implements ExceptionMapper<IOException> {

    @Override
    public Response toResponse(IOException e) {
        System.out.println(e);
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
