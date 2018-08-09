package com.semantyca.nb.core.dataengine.jpa.model;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalDateTimeConverter;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.UUIDConverter;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.jaxrs.LocalDateConverter;
import org.apache.johnzon.mapper.JohnzonConverter;
import org.apache.johnzon.mapper.JohnzonIgnore;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@UuidGenerator(name = "uuid-gen")
public abstract class AppEntity implements IAppEntity {

    @Id
    @GeneratedValue(generator = "uuid-gen")
    @Convert(converter = UUIDConverter.class)
    @Column(name = "id", nullable = false, columnDefinition = "uuid", updatable = false)
    @JohnzonConverter(com.semantyca.nb.core.dataengine.jpa.model.convertor.jaxrs.UUIDConverter.class)
    @JohnzonIgnore
    private UUID id;

    @JoinColumn(name = "author", nullable = false, updatable = false)
    private Long author;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "reg_date", nullable = false, updatable = false)
    @JohnzonConverter(LocalDateConverter.class)
    @JsonbDateFormat("dd.MM.yyyy kk:mm")
    private LocalDateTime regDate;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "last_mod_date", nullable = false)
    @JohnzonConverter(LocalDateConverter.class)
    @JsonbDateFormat("dd.MM.yyyy kk:mm")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @Column(name = "last_mod_user", nullable = false)
    private Long lastModifier;

    private String title;

    @Transient
    private boolean editable = true;

    @Transient
    private boolean wasRead = true;

    @Transient
    private boolean deleted;

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JohnzonIgnore
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

    @JohnzonIgnore
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JohnzonIgnore
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

    @Override
    public boolean isNew() {
        return id == null;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
