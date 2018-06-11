package com.semantyca.nb.core.control;

import com.semantyca.nb.administrator.entity.User;
import com.semantyca.nb.core.rest.security.AuthenticatedUserSession;
import com.semantyca.nb.core.rest.security.UserSession;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Stateless
public class Auth {

    @PersistenceContext(unitName = "Administrator")
    EntityManager em;

    @Inject
    @AuthenticatedUserSession
    Event<UserSession> userAuthenticatedEvent;

    private static Map authorizationTokens = new HashMap();

    public UserSession authenticate(String userName, String password) {

        try {
            User user = findUser(userName);
            if (user != null) {
                UserSession userSession = new UserSession();
                userSession.setUser(user);
                String authToken = issueToken(userName);
                userSession.setToken(authToken);
                userAuthenticatedEvent.fire(userSession);
                authorizationTokens.put(authToken, userSession);
                return userSession;
            }else{
                return null;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    private String issueToken(String userName){
        String authToken = UUID.randomUUID().toString();
        return authToken;
    }

    private User findUser(String userName) {
        TypedQuery<User> query = em.createNamedQuery("findById", User.class);
        query.setParameter("name", userName);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return user;
    }
}
