package com.semantyca.nb.core.dataengine.jpa;

import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;

import java.util.List;

public interface IDAO<T, K> extends ISimpleDAO<T> {

    T findByIdIfExist(String id);

    T findByIdIfExist(K id);

    T findById(K id);

    T findById(String id);


    List<T> findAll(IFilter<T> filter);

    List<T> findAll(IFilter<T> filter, SortParams sort);

 //   List<T> findAll(IFilter<T> filter, SortParams sort, DAO.READ_ACCESS readAccess);

 //   List<T> findAll(IFilter<T> filter, SortParams sort, GenericConverter<T, T> converter, DAO.READ_ACCESS readAccess);


    ViewPage findViewPage(int pageNum, int pageSize);

    ViewPage findViewPage(SortParams sort, int pageNum, int pageSize);

  //  ViewPage<T> findViewPage(GenericConverter<T, T> converter, SortParams sort, int pageNum, int pageSize);

    ViewPage findViewPage(IFilter<T> filter, SortParams sort, int pageNum, int pageSize);

 //   ViewPage<T> findViewPage(IFilter<T> filter, GenericConverter<T, T> converter, SortParams sort, int pageNum, int pageSize);


    Long getCount();

    Long count(IFilter<T> filter);


    T save(T entity);

    T add(T entity);

    T update(T entity);

}
