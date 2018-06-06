package com.semantyca.essential.boundary;

import com.semantyca.essential.administrator.entity.Group;
import com.semantyca.essential.administrator.entity.User;
import com.semantyca.essential.util.StringUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Stateless
public class SessionProvider {
    
    
    @PersistenceUnit(unitName = "Administrator")
    private EntityManager em;

    public User createUser(User user) {
        try {
            user.setPassword(StringUtil.encodeSHA256(user.getPassword()));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
           // Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        Group group = new Group();
        group.setEmail(user.getEmail());
        group.setGroupname(Group.USERS_GROUP);
        em.persist(user);
        em.persist(group);

        return user;
    }

    public User findUserById(String id) {
      //  if (em == null) Lg.info("em is null");
        TypedQuery<User> query = em.createNamedQuery("findById", User.class);
        query.setParameter("email", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }
        return user;
    }
}
