package com.semantyca.nb.core.dataengine.jpa.dao;


import com.semantyca.nb.core.dataengine.DataengineUtil;
import com.semantyca.nb.core.dataengine.jpa.ISimpleDAO;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.ui.view.ViewPage;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

//@Stateless
public abstract class SimpleDAO<T, K> implements ISimpleDAO<T, K> {
    public IUser user;
    protected Session ses;
    protected Class<T> entityClass;

    @PostConstruct
    protected void init() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PersistenceContext(unitName = "nb4")
    protected EntityManager em;

    public T findById(String id) {
        return findById((K) id);
    }

    @Override
    public T findById(K id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        Predicate condition = c.get("id").in(id);
        cq.where(condition);
        Query query = em.createQuery(cq);
        T entity = (T) query.getSingleResult();
        return entity;
    }

    @Override
    public T add(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        em.merge(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public ViewPage findAll(int pageNum, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        try {
            CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
            CriteriaQuery<Long> countCq = builder.createQuery(Long.class);
            Root<T> root = criteriaQuery.from(entityClass);
            criteriaQuery.select(root);
            countCq.select(builder.count(root));
            //     criteriaQuery.orderBy(builder.asc(root.get("regDate")));

            TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
            Query query = em.createQuery(countCq);
            long count = (long) query.getSingleResult();
            int maxPage = setupPaging(typedQuery, count, pageNum, pageSize);

            List<T> result = typedQuery.getResultList();
            ViewPage r = new ViewPage(result, count, maxPage, pageNum);
            return r;
        } catch (Exception e) {
            Lg.exception(e);
        }
        return null;
    }


    protected int setupPaging(TypedQuery typedQuery, long count, int pageNum, int pageSize) {
        int maxPage = 1;
        if (pageNum != 0 || pageSize != 0) {
            maxPage = DataengineUtil.countMaxPage(count, pageSize);
            if (pageNum < 0) {
                pageNum = 1;
            }
            int firstRec = DataengineUtil.calcStartEntry(pageNum, pageSize);
            typedQuery.setFirstResult(firstRec);
            typedQuery.setMaxResults(pageSize);
        }
        return maxPage;
    }


}
