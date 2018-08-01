package com.semantyca.nb.cli.task;

import com.semantyca.nb.core.rest.security.Session;

public abstract class Do extends AbstractServerTask {

    @Override
    public abstract void doTask(Session session);



}
