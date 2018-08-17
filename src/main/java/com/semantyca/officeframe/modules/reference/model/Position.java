package com.semantyca.officeframe.modules.reference.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.officeframe.modules.reference.init.ModuleConst;

import javax.persistence.*;

@Entity
@Cacheable(true)
@Table(name = ModuleConst.CODE + "__positions", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NamedQuery(name = "Position.findAll", query = "SELECT m FROM Position AS m ORDER BY m.regDate")
public class Position extends SimpleReferenceEntity {
    private int rank = 999;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
