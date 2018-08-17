package com.semantyca.skyra.modules.operator.dto;

import com.semantyca.nb.core.dataengine.jpa.dto.IDTO;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;

import java.util.UUID;

public class ExplorationViewEntry implements IDTO {

    public UUID id;
    public ExplorationStatus status;

    public ExplorationViewEntry(UUID id, ExplorationStatus status) {
        this.id = id;
        this.status = status;
    }
}
