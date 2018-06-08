package com.semantyca.nb.core.boundary;

import com.semantyca.administrator.entity.Group;
import com.semantyca.administrator.entity.User;
import com.semantyca.nb.util.StringUtil;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Stateless
public class SessionProvider {

    @PersistenceContext(unitName = "Administrator")
    EntityManager em;

    
    @PostConstruct
    public void init(){
         System.out.println("----------------Session provider injected");
         System.out.println("em=" + em);
    }
    
    
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
    
        public long usersCount() {
        try {
            Query q = em.createQuery("SELECT count(email) FROM User AS m");
            return (Long) q.getSingleResult();
        } catch (Exception e) {
              e.fillInStackTrace();
return 0 ;              
        } 
    }
}
