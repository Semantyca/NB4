package com.semantyca.nb.modules.administrator.dao;

import com.semantyca.nb.core.dataengine.jpa.dao.SimpleDAO;
import com.semantyca.nb.modules.administrator.dto.UserViewEntry;
import com.semantyca.nb.modules.administrator.model.User;
import com.semantyca.nb.ui.view.ViewPage;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class UserDAO extends SimpleDAO<User, Long> {



    public ViewPage findViewPage(int pageNum, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserViewEntry> cq = cb.createQuery(UserViewEntry.class);
        CriteriaQuery<Long> countRootCq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);

        Predicate condition = null;


        cq.select(cb.construct(
                UserViewEntry.class,
                root.get("id"),
                root.get("login"),
                root.get("email")
        ));


        countRootCq.select(cb.countDistinct(root));

        if (condition != null) {
            cq.where(condition);
            countRootCq.where(condition);
        }
        cq.orderBy(cb.desc(root.get("lastModifiedDate")));

        TypedQuery<UserViewEntry> typedQuery = em.createQuery(cq);
        TypedQuery<Long> countQuery = em.createQuery(countRootCq);

        long count = countQuery.getSingleResult();
        setupPaging(typedQuery, pageNum, pageSize, count);
        ViewPage vp = new ViewPage("Users", typedQuery.getResultList(), count, pageSize, pageNum);
        if (vp.getResult().isEmpty()) {
            return vp;
        }

        return vp;

    }

    public User findByLogin(String login) {
        String jpql = "SELECT m FROM User AS m WHERE m.login = :login";
        TypedQuery<User> q = em.createQuery(jpql, User.class);
        q.setParameter("login", login);
        User user = q.getSingleResult();
        return user;
    }


}
