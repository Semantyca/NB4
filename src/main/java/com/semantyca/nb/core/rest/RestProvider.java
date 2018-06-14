package com.semantyca.nb.core.rest;

import com.semantyca.nb.core.rest.security.Session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

public abstract class RestProvider{

    @Context
    protected ServletContext context;
    @Context
    protected HttpServletRequest httpRequest;
    @Context
    protected HttpServletResponse response;

    private Session userSession;
    private WebFormData webFormData;




    protected WebFormData getWebFormData() {
        if (webFormData == null) {
            webFormData = new WebFormData(httpRequest.getParameterMap());
        }
        return webFormData;
    }

    public void setWebFormData(WebFormData webFormData) {
        this.webFormData = webFormData;
    }






}
