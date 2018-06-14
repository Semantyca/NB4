package com.semantyca.nb.modules.administrator.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.LocalDateTimeConverter;
import com.semantyca.nb.core.user.IUser;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="_users")
@NamedQueries({
        @NamedQuery(name = "findByName", query = "SELECT u FROM User u WHERE u.name = :name")
})
public class User extends SimpleAppEntity implements IUser {

    @Column(name="email", nullable=false)
    private String email;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "reg_date", nullable = false, updatable = false)
    protected LocalDateTime regDate;

    @JsonbTransient
    @Column(name="password", nullable=false, length=64)
    private String password;

    @Column(name="name", nullable=false, length=30)
    private String name;

    public User(){}

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    private void prePersist() {
        regDate = LocalDateTime.now();
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public  boolean isSuperUser(){
        return false;
    }

}