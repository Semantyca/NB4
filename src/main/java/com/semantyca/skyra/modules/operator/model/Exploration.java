package com.semantyca.skyra.modules.operator.model;

import com.semantyca.nb.core.dataengine.jpa.model.AppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.EntityAttachment;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalDateTimeDbConverter;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.jaxrs.LocalDateTimeJSONConverter;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import com.semantyca.skyra.modules.operator.init.ModuleConst;
import org.apache.johnzon.mapper.JohnzonConverter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = ModuleConst.CODE + "__explorations")
public class Exploration extends AppEntity {

    @Convert(converter = LocalDateTimeDbConverter.class)
    @Column(name = "start_time", nullable = false, updatable = false)
    @JohnzonConverter(LocalDateTimeJSONConverter.class)
    @JsonbDateFormat(LocalDateTimeJSONConverter.DATE_TIME_JSON_FORMAT)
    private LocalDateTime startTime;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private ExplorationStatus status;

    @ElementCollection
    @MapKey(name = "fileName")
    @CollectionTable(joinColumns = @JoinColumn(name = "entity_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"entity_id", "files"}))
    private Map<Long, EntityAttachment> files = new HashMap<>();

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public ExplorationStatus getStatus() {
        return status;
    }

    public void setStatus(ExplorationStatus status) {
        this.status = status;
    }
}
