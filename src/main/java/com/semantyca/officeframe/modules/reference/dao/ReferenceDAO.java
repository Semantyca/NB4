package com.semantyca.officeframe.modules.reference.dao;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.dao.DAO;

/**
 * @author Kayra 10-01-2016
 * <p>
 * Common superclass for all entities of the Reference module. To more
 * fine tuning reload it
 */
public abstract class ReferenceDAO<T extends IAppEntity> extends DAO<T> {


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
