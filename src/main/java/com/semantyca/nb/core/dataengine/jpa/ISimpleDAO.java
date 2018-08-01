package com.semantyca.nb.core.dataengine.jpa;


import com.semantyca.nb.ui.view.ViewPage;

public interface ISimpleDAO<T, K> {

    T findById(String id);

    T findById(K id);

    T add(T entity);

    T update(T entity);

    void delete(T entity);

    ViewPage findViewPage(int pageNum, int pageSize);

}
