package com.semantyca.nb.core.dataengine.jpa;

import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.ui.view.ViewPage;

import java.util.List;

public interface IDAO<T, K> extends ISimpleDAO<T, K> {

    List<T> findAll(IFilter<T> filter);

    List<T> findAll(IFilter<T> filter, SortParams sort);

    ViewPage findViewPage(SortParams sort, int pageNum, int pageSize);

    ViewPage findViewPage(IFilter<T> filter, SortParams sort, int pageNum, int pageSize);

    T save(T entity);

}
