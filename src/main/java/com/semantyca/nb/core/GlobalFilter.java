package com.semantyca.nb.core;

import com.semantyca.nb.core.rest.security.AuthenticatedUserSession;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.SuperUser;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class GlobalFilter  implements ContainerRequestFilter {

    @Inject
    @AuthenticatedUserSession
    private Event<Session> userAuthenticatedEvent;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Session userSession = new Session();
        //userSession.setUser(new AnonymousUser());
        userSession.setUser(new SuperUser()); //just for testing
        userAuthenticatedEvent.fire(userSession);



        return;
    }
}
