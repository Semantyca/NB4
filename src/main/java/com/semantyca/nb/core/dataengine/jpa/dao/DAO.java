package com.semantyca.nb.core.dataengine.jpa.dao;


import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.IDAO;
import com.semantyca.nb.core.dataengine.jpa.IFilter;
import com.semantyca.nb.core.rest.security.Session;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;

import javax.persistence.EntityManager;
import java.util.List;


public abstract class DAO<T extends IAppEntity,K> extends SimpleDAO<T, K> implements IDAO<T,K> {
    public IUser user;
    protected Session ses;
    private Class<T> entityClass;

    protected EntityManager em;

    public DAO(Class entityClass, Session session) {

    }


    public T findByIdIfExist(K id) {
        return null;
    }

    public T findById(K id) {
        return null;
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
    public ViewPage findViewPage(int pageNum, int pageSize) {
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
    public Long getCount() {
        return null;
    }

    @Override
    public Long count(IFilter<T> filter) {
        return null;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public T add(T entity) {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }


}
