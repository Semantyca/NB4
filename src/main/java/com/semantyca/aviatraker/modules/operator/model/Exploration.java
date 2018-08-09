package com.semantyca.aviatraker.modules.operator.model;

import com.semantyca.aviatraker.modules.operator.init.ModuleConst;
import com.semantyca.nb.core.dataengine.jpa.model.AppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.EntityAttachment;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = ModuleConst.CODE + "__explorations")
public class Exploration extends AppEntity {

    @ManyToOne(optional = false)
    private ExplorationStatus status;

    @ElementCollection
    @MapKey(name = "fileName")
    @CollectionTable(joinColumns = @JoinColumn(name = "entity_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"entity_id", "files"}))
    private Map<Long, EntityAttachment> files = new HashMap<>();

    public ExplorationStatus getStatus() {
        return status;
    }

    public void setStatus(ExplorationStatus status) {
        this.status = status;
    }
}
