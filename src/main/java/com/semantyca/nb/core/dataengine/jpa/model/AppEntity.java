package com.semantyca.nb.core.dataengine.jpa.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalDateTimeDbConverter;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink.ELUUIDConverter;
import com.semantyca.nb.core.rest.serializer.CustomDateTimeDeserializer;
import com.semantyca.nb.core.rest.serializer.CustomDateTimeSerializer;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@org.eclipse.persistence.annotations.Converter(name = "uuidConverter", converterClass = ELUUIDConverter.class)
@UuidGenerator(name = "uuid-gen")
@JsonPropertyOrder({"type", "id", "existenceState", "title", "regDate", "wasRead", "authorId", "editable"})
public abstract class AppEntity implements IAppEntity {

    @Id
    @GeneratedValue(generator = "uuid-gen")
    @org.eclipse.persistence.annotations.Convert("uuidConverter")
    @Column(name = "id", nullable = false, columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "author", nullable = false, updatable = false)
    private Long author;

    @Convert(converter = LocalDateTimeDbConverter.class)
    @Column(name = "reg_date", nullable = false, updatable = false)
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime regDate = LocalDateTime.now();

    @Convert(converter = LocalDateTimeDbConverter.class)
    @Column(name = "last_mod_date", nullable = false)
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_mod_user", nullable = false)
    private Long lastModifier;

    private String title;

    @Transient
    private boolean editable = true;

    @Transient
    private boolean wasRead = true;

    private ExistenceState existenceState;

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    @Override
    public Long getAuthor() {
        return author;
    }


    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    @Override
    public String toString() {
        if (getId() == null) {
            return "id is null," + this.getClass().getName();
        }
        UUID id = UUID.fromString(getId().toString());
        return id.toString() + "," + this.getClass().getName();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void setNew(boolean aNew) {
        if (aNew) id = null;
    }


    @Override
    public boolean isEditable() {
        return editable;
    }

    @Override
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }

    @Override
    public ExistenceState getExistenceState() {
        return existenceState;
    }

    public void setExistenceState(ExistenceState existenceState) {
        this.existenceState = existenceState;
    }
}
