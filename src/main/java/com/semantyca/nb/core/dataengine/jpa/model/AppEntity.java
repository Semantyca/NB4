package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.LocalDateTimeConverter;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.UUIDConverter;
import com.semantyca.nb.modules.administrator.model.User;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Converter(name = "uuidConverter", converterClass = UUIDConverter.class)
@UuidGenerator(name = "uuid-gen")
public abstract class AppEntity implements IAppEntity {

    @Id
    @GeneratedValue(generator = "uuid-gen")
    @Convert("uuidConverter")
    @Column(name = "id", nullable = false)
    protected UUID id;

    @JoinColumn(name = "author", nullable = false, updatable = false)
    private Long author;

    @javax.persistence.Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @javax.persistence.Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "last_mod_date", nullable = false)
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

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Long getAuthor() {
        return author;
    }

    @Override
    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getLastModifiedDate() {
         return lastModifiedDate;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    @Override
    public void setLastModifier(User user) {
        lastModifier = user.getId();
        lastModifiedDate = LocalDateTime.now();
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


    public String getURL(){
        return id.toString();
    }

}
