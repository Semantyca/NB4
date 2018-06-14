package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.UUIDConverter;
import com.semantyca.nb.modules.administrator.model.User;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;
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
    private User author;

    @Column(name = "reg_date", nullable = false, updatable = false)
    private Date regDate;

    @Column(name = "last_mod_date", nullable = false)
    private Date lastModifiedDate = new Date();

    @Column(name = "last_mod_user", nullable = false)
    private User lastModifier;

    private String title;

    @Transient
    private boolean editable = true;

    @Transient
    private boolean wasRead = true;

    @Transient
    private boolean hasAttachments;

    @Transient
    private boolean deleted;

    @Override
    public UUID getId() {
        return id;
    }

    public String getIdentifier() {
        if (id != null) {
            return id.toString();
        } else {
            return "null";
        }
    }

    @Override
    public User getAuthor() {
        return author;
    }



    @Override
    public void setAuthor(User user) {
        author = user;
    }

    @Override
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLastModifiedDate() {
         return lastModifiedDate;
    }

    public User getLastModifier() {
        return lastModifier;
    }

    @Override
    public void setLastModifier(User user) {
        lastModifier = user;
        lastModifiedDate = new Date();
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
    public String getURL() {
        return getIdentifier();
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
