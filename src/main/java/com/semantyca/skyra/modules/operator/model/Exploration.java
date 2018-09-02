package com.semantyca.skyra.modules.operator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import com.semantyca.skyra.modules.operator.model.constants.converter.LatLngConverter;
import com.semantyca.skyra.modules.operator.model.constants.converter.PathFaceConverter;
import com.semantyca.skyra.modules.operator.model.embedded.PathFaceOptions;
import com.semantyca.skyra.modules.operator.model.embedded.PointCoordiantes;
import com.semantyca.skyra.modules.operator.other.KMLParser;

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

    @Convert(converter = LatLngConverter.class)
    @Column(name = "lat_lng", columnDefinition = "jsonb")
    @JsonProperty("path")
    private List<PointCoordiantes> flightPath = new ArrayList<>();

    @Convert(converter = PathFaceConverter.class)
    @Column(name = "path_options", columnDefinition = "jsonb")
    @JsonProperty("polylineOptions")
    private PathFaceOptions options;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "entity_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"entity_id", "filename"}))
    private List<EntityAttachment> files;

    @PreUpdate
    public void postUpdate() {
        if (files != null) {
            for (EntityAttachment attachment : files) {
                KMLParser parser = new KMLParser();
                flightPath.clear();
                flightPath.addAll(parser.process(attachment.getFile()));
            }
        }

        if (options == null) {
            options = new PathFaceOptions();
        }
        options.setColor(status.getColor());

    }

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

    public List<PointCoordiantes> getFlightPath() {
        return flightPath;
    }

    public void setFlightPath(List<PointCoordiantes> flightPath) {
        this.flightPath = flightPath;
    }

    public PathFaceOptions getOptions() {
        return options;
    }

    public void setOptions(PathFaceOptions options) {
        this.options = options;
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
