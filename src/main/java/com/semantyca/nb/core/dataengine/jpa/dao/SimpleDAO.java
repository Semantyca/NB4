package com.semantyca.nb.core.dataengine.jpa.dao;


import com.semantyca.nb.core.dataengine.DataengineUtil;
import com.semantyca.nb.core.dataengine.jpa.ISimpleAppEntity;
import com.semantyca.nb.core.dataengine.jpa.ISimpleDAO;
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


public abstract class SimpleDAO<T extends ISimpleAppEntity, K> implements ISimpleDAO<T, K> {

    protected Class<T> entityClass;

    @PostConstruct
    protected void init() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PersistenceContext(unitName = "nb4")
    protected EntityManager em;

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
        List<T> result = typedQuery.getResultList();
        return result;
    }

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
        entity = em.merge(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }

    @Override
    public ViewPage findViewPage(int pageNum, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        CriteriaQuery<Long> countCq = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        countCq.select(builder.count(root));
        criteriaQuery.orderBy(builder.desc(root.get("regDate")));

        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
        Query query = em.createQuery(countCq);
        Long count = (Long) query.getSingleResult();
        setupPaging(typedQuery, pageNum, pageSize, count);

        List<T> result = typedQuery.getResultList();
        ViewPage r = new ViewPage(entityClass.getSimpleName() + "s", result, count, pageSize, pageNum);
        return r;
    }


    protected void setupPaging(TypedQuery typedQuery, int pageNum, int pageSize, long count) {
        if (pageNum != 0 || pageSize != 0) {

            if (pageNum < 0) {
                pageNum = 1;
            } else {
                int maxPage = countMaxPage(count, pageSize);
                if (pageNum > maxPage) {
                    pageNum = maxPage;
                }
            }
            int firstRec = DataengineUtil.calcStartEntry(pageNum, pageSize);
            typedQuery.setFirstResult(firstRec);
            typedQuery.setMaxResults(pageSize);
        }

    }

    public static int countMaxPage(long colCount, int pageSize) {
        float mp = (float) colCount / (float) pageSize;
        float d = Math.round(mp);

        int maxPage = (int) d;
        if (mp > d) {
            maxPage++;
        }
        if (maxPage < 1) {
            maxPage = 1;
        }
        return maxPage;
    }
}
