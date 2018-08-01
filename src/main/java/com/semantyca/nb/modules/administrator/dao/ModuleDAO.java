package com.semantyca.nb.modules.administrator.dao;

import com.semantyca.nb.core.dataengine.jpa.dao.SimpleDAO;
import com.semantyca.nb.logger.Lg;
import com.semantyca.nb.modules.administrator.model.Module;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
public class ModuleDAO extends SimpleDAO<Module, UUID> {

    @PersistenceContext(unitName = "nb4")
    protected EntityManager em;

    @PostConstruct
    protected void init() {
        Lg.info(em.toString());
    }

}
