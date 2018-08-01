package com.semantyca.nb.modules.administrator.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalDateTimeConverter;
import com.semantyca.nb.modules.administrator.init.ModuleConst;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = ModuleConst.CODE + "__groups")
public class Group extends SimpleAppEntity {

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "reg_date", nullable = false, updatable = false)
    protected LocalDateTime regDate;

    @Column(name="email", nullable=false, length=255)
    private String email;

    @Column(name="groupname", nullable=false, length=32)
    private String groupname;

    public Group() {}

    public Group(String email, String groupname) {
        this.email = email;
        this.groupname = groupname;
    }

    @PrePersist
    private void prePersist() {
        regDate = LocalDateTime.now();
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}

