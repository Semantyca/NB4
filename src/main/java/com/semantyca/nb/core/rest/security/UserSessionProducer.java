package com.semantyca.nb.core.rest.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class UserSessionProducer {

    @Named("AuthenticatedUserSession")
    @Produces
    @RequestScoped
    private UserSession userSession;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUserSession UserSession userSession) {
        this.userSession = userSession;
    }

}
