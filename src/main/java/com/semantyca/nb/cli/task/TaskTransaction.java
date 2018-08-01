package com.semantyca.nb.cli.task;

import com.semantyca.nb.core.dataengine.jpa.ISimpleDAO;

//@EJB
public class TaskTransaction<T,K> {
    ISimpleDAO<T,K> dao;

    public void setDAO(ISimpleDAO dao){
        this.dao = dao;

    }

    public void add(T entity){
        dao.add(entity);
    }

}
