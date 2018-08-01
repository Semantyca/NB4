package com.semantyca.nb.cli.task;

import com.semantyca.nb.core.rest.security.Session;

public abstract class AbstractServerTask  implements IServerScript {
    protected ServerTaskOutcome outcome;
    private Session session;

    public ServerTaskOutcome processCode() {
        doTask(getSes());
        return outcome;
    }

    public void setOutcome(ServerTaskOutcome outcome) {
        this.outcome = outcome;

    }

    protected void setError(Exception e) {
        outcome.setType(InfoMessageType.SERVER_TASK_ERROR);
        outcome.setException(e);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    public abstract void doTask(Session session);

    public Session getSes() {
        return session;
    }

    public void setSession(Session ses) {
        this.session = ses;
    }

}
