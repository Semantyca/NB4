package com.semantyca.aviatraker.modules.operator.model;

import com.semantyca.aviatraker.modules.operator.init.ModuleConst;
import com.semantyca.nb.core.dataengine.jpa.model.AppEntity;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = ModuleConst.CODE + "__explorations")
public class Exploration extends AppEntity {

    @ManyToOne(optional = false)
    private ExplorationStatus status;

    public ExplorationStatus getStatus() {
        return status;
    }

    public void setStatus(ExplorationStatus status) {
        this.status = status;
    }
}
