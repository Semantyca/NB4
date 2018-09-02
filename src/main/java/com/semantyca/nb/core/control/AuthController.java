package com.semantyca.nb.core.control;

import com.semantyca.nb.core.rest.security.AuthenticatedUserSession;
import com.semantyca.nb.core.rest.security.Session;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Stateless
public class AuthController {

    @Inject
    @AuthenticatedUserSession
    private Event<Session> userAuthenticatedEvent;

    private static Map authorizationTokens = new HashMap();

    public Session authenticate(String userName, String password) {

        try {
            Session userSession = new Session();
            userSession.authenticate(userName, password);
            if (userSession.isAuthenticated()) {
                String authToken = issueToken(userName);
                userSession.setToken(authToken);
                userAuthenticatedEvent.fire(userSession);
                authorizationTokens.put(authToken, userSession);
            }
            return userSession;

        } catch (Exception e) {
            throw e;
        }
    }

    private String issueToken(String userName) {
        String authToken = UUID.randomUUID().toString();
        return authToken;
    }


    @FunctionalInterface
    interface Square {
        int calculate(int x);
    }

    @FunctionalInterface
    interface Merger {
        String plus(String x);
    }

    public static void main(String args[]) {
        int a = 5;

        // lambda expression to define the calculate method

        Merger m = (String val) -> val + "-" + val;

        // parameter passed and return type must be
        // same as defined in the prototype

        String res = m.plus("bla");
        System.out.println(res);
    }

}
