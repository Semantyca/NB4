package com.semantyca.officeframe.modules.reference.dao;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.dao.DAO;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Kayra 10-01-2016
 * <p>
 * Common superclass for all entities of the Reference module. To more
 * fine tuning reload it
 */
public abstract class ReferenceDAO<T extends IAppEntity> extends DAO<T> {


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
