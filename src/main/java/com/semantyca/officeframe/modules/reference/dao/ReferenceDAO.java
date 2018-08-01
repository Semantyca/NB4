package com.semantyca.officeframe.modules.reference.dao;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.dao.DAO;
import com.semantyca.nb.ui.view.ViewPage;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Kayra 10-01-2016
 * <p>
 * Common superclass for all entities of the Reference module. To more
 * fine tuning reload it
 */
public abstract class ReferenceDAO<T extends IAppEntity> extends DAO<T> {


    public List<T> findAllCategories() {
        try {
            Query query = em.createQuery("SELECT e.category FROM " + entityClass.getName()
                    + " AS e WHERE e.category != NULL AND e.category != '' GROUP BY e.category");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public T findByIdentifier(String identifier) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        Predicate condition = cb.equal(cb.lower(c.get("identifier")), identifier.toLowerCase());
        cq.where(condition);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();
    }

    public List<T> findAllByCategory(String categoryName) {
        return findAllByCategory(categoryName, 0, 0).getResult();
    }

    public T findByNameAndCategory(String category, String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        Predicate condition1 = cb.equal(cb.lower(c.get("category")), category.toLowerCase());
        Predicate condition2 = cb.equal(cb.lower(c.get("name")), name.toLowerCase());
        Predicate condition = cb.and(condition1, condition2);
        cq.where(condition);
        TypedQuery<T> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();

    }

    public T findByCode(Enum<?> code) {
        try {
            String jpql = "SELECT m FROM " + entityClass.getName() + " AS m WHERE m.code = :code";
            TypedQuery<T> q = em.createQuery(jpql, entityClass);
            q.setParameter("code", code);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public ViewPage findAllByCategory(String categoryName, int pageNum, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        countCq.select(cb.count(c));
        if (!categoryName.isEmpty()) {
            Predicate condition = cb.like(cb.lower(c.get("category")), categoryName);
            cq.where(condition);
            countCq.where(condition);
        }
        TypedQuery<T> typedQuery = em.createQuery(cq);
        Query query = em.createQuery(countCq);
        long count = (long) query.getSingleResult();
        setupPaging(typedQuery, pageNum, pageSize, count);
        List<T> result = typedQuery.getResultList();
        return new ViewPage(entityClass.getSimpleName() + "s", result, count, pageSize, pageNum);

    }

    @Override
    public T add(T entity) {
        T e = super.add(entity);
        resetCache();
        return e;
    }

    @Override
    public T update(T entity) {
        T e = super.update(entity);
        resetCache();
        return e;
    }

    @Override
    public void delete(T entity) {
        super.delete(entity);
        resetCache();
    }


    public boolean resetCache() {
 //       getEntityManagerFactory().getCache().evict(entityClass);
        return true;
    }

}
