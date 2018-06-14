package com.semantyca.nb.core.rest.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@RequestScoped
public class UserSessionProducer {

    @Named("AuthenticatedUserSession")
    @Produces
    @RequestScoped
    private Session userSession;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUserSession Session userSession) {
        this.userSession = userSession;
    }

}
