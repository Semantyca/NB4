package com.semantyca.nb.cli.task;


import com.semantyca.nb.core.rest.security.Session;

public interface IServerScript {

    void setSession(Session ses);

    String getName();

}
