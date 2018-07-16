package com.semantyca.nb.core.service;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.env.Environment;
import com.semantyca.nb.util.TimeUtil;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
@RequestScoped
public class DefaultResource {

    @GET
    public String getDefault() {
        return EnvConst.SERVER_NAME + " " + EnvConst.SERVER_VERSION  + ", up time: " + TimeUtil.dateTimeToStringSilently(Environment.startTime);
    }

}
