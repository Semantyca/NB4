package com.semantyca.aviatraker.modules.operator.dto;

import com.semantyca.aviatraker.modules.operator.model.constants.RouteStatus;
import com.semantyca.nb.core.dataengine.jpa.dto.IDTO;

import java.util.UUID;

public class RouteViewEntry implements IDTO {

    public UUID id;
    public RouteStatus status;

    public RouteViewEntry(UUID id, RouteStatus status) {
        this.id = id;
        this.status = status;
    }
}
