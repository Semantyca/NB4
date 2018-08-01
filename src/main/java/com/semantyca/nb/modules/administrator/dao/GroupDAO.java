package com.semantyca.nb.modules.administrator.dao;

import com.semantyca.nb.core.dataengine.jpa.dao.SimpleDAO;
import com.semantyca.nb.modules.administrator.model.Group;
import com.semantyca.nb.modules.administrator.model.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class GroupDAO extends SimpleDAO<Group, Long> {



    public User findByLogin(String login) {
        String jpql = "SELECT m FROM User AS m WHERE m.login = :login";
        TypedQuery<User> q = em.createQuery(jpql, User.class);
        q.setParameter("login", login);
        User user = q.getSingleResult();
        return user;
    }


}
