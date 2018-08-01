package com.semantyca.aviatraker.modules.operator.model;

import com.semantyca.aviatraker.modules.operator.init.ModuleConst;
import com.semantyca.aviatraker.modules.operator.model.constants.RouteStatus;
import com.semantyca.aviatraker.modules.operator.model.constants.converter.RouteStatusConvertor;
import com.semantyca.nb.core.dataengine.jpa.model.AppEntity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ModuleConst.CODE + "__routes")
public class Route extends AppEntity {

    @Convert(converter = RouteStatusConvertor.class)
    private RouteStatus status = RouteStatus.UNKNOWN;

    public RouteStatus getStatus() {
        return status;
    }

    public void setStatus(RouteStatus status) {
        this.status = status;
    }
}
