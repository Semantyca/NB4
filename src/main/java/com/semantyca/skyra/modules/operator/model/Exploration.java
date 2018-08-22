package com.semantyca.skyra.modules.operator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semantyca.nb.core.dataengine.jpa.IEntityWithAttachments;
import com.semantyca.nb.core.dataengine.jpa.model.AppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.EntityAttachment;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalDateTimeDbConverter;
import com.semantyca.nb.core.rest.serializer.CustomDateTimeDeserializer;
import com.semantyca.nb.core.rest.serializer.CustomDateTimeSerializer;
import com.semantyca.officeframe.modules.organizations.model.Employee;
import com.semantyca.officeframe.modules.organizations.model.converter.EmployeeConverter;
import com.semantyca.officeframe.modules.reference.model.ExplorationStatus;
import com.semantyca.skyra.modules.operator.init.ModuleConst;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ModuleConst.CODE + "__explorations")
@org.eclipse.persistence.annotations.Converter(name = "emp_conv", converterClass = EmployeeConverter.class)
public class Exploration extends AppEntity implements IEntityWithAttachments {

    @Convert(converter = LocalDateTimeDbConverter.class)
    @Column(name = "start_time")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime startTime;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(columnDefinition = "uuid not null")
    private ExplorationStatus status;

    @ManyToOne
    @JoinColumn(columnDefinition = "uuid", name = "pilot1_id")
    private Employee pilot1;

    @ManyToOne
    @JoinColumn(columnDefinition = "uuid", name = "pilot2_id")
    private Employee pilot2;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "entity_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"entity_id", "filename"}))
    private List<EntityAttachment> files = new ArrayList<>();

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Employee getPilot1() {
        return pilot1;
    }

    public void setPilot1(Employee pilot1) {
        this.pilot1 = pilot1;
    }

    public Employee getPilot2() {
        return pilot2;
    }

    public void setPilot2(Employee pilot2) {
        this.pilot2 = pilot2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ExplorationStatus getStatus() {
        return status;
    }

    public void setStatus(ExplorationStatus status) {
        this.status = status;
    }

    public List<EntityAttachment> getFiles() {
        return files;
    }

    public void setFiles(List<EntityAttachment> files) {
        this.files = files;
    }
}
