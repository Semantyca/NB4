package com.semantyca.nb.core.dataengine.jpa.dao;


import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.IDAO;
import com.semantyca.nb.core.dataengine.jpa.IFilter;
import com.semantyca.nb.core.dataengine.jpa.model.ISecureAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.embedded.Reader;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public abstract class DAO<T extends IAppEntity> extends SimpleDAO<T, UUID> implements IDAO<T, UUID> {

    @Inject
    @Named("AuthenticatedUserSession")
    protected Session session;

    public T findById(String id){
        try {
            return findById(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            Lg.error(e.toString());
            return null;
        }
    }

    public T findById(UUID id){
        IUser user = session.getUser();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        boolean isSecureEntity = false;
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        Predicate condition = c.get("id").in(id);
        if (!user.isSuperUser() && ISecureAppEntity.class.isAssignableFrom(entityClass)) {
            MapJoin<T, Long, Reader> mapJoin = c.joinMap("readers");
            condition = cb.and(cb.equal(mapJoin.key(), user.getId()), condition);
            isSecureEntity = true;
        }
        cq.where(condition);
        Query query = em.createQuery(cq);
        T entity = (T) query.getSingleResult();

        if (isSecureEntity) {
            if (!((ISecureAppEntity) entity).getEditors().contains(user.getId())) {
                entity.setEditable(false);
            }
        }
        return entity;

    }

    @Override
    public List<T> findAll(IFilter<T> filter) {
        return null;
    }

    @Override
    public List<T> findAll(IFilter<T> filter, SortParams sort) {
        return null;
    }

    @Override
    public ViewPage findViewPage(SortParams sort, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ViewPage findViewPage(IFilter<T> filter, SortParams sort, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            return add(entity);
        }
        return update(entity);
    }

    @Override
    public T add(T entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setRegDate(now);
        entity.setLastModifiedDate(now);
        Long currenUserId = session.getUser().getId();
        entity.setLastModifier(currenUserId);
        entity.setAuthor(currenUserId);
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setLastModifiedDate(now);
        entity.setLastModifier(session.getUser().getId());
        em.merge(entity);
        return entity;
    }


}
