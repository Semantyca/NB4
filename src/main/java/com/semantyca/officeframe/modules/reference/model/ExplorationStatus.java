package com.semantyca.officeframe.modules.reference.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.officeframe.modules.reference.init.ModuleConst;

import javax.persistence.*;

@Entity
@Cacheable(true)
@Table(name = ModuleConst.CODE + "__explorationstatuses", uniqueConstraints = @UniqueConstraint(columnNames = {"identifier"}))
public class ExplorationStatus extends SimpleReferenceEntity {

    @Column(length = 7)
    private String color = "#000000";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
