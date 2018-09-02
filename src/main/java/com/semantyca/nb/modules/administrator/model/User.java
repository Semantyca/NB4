package com.semantyca.nb.modules.administrator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.semantyca.nb.core.dataengine.jpa.model.SimpleAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalDateTimeDbConverter;
import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.core.user.constants.UserStatusCode;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.init.ModuleConst;
import com.semantyca.nb.modules.administrator.model.convertor.LanguageCodeConverter;
import com.semantyca.nb.modules.administrator.model.convertor.UserStatusCodeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = ModuleConst.CODE + "__users")
@NamedQueries({
        @NamedQuery(name = "findByName", query = "SELECT u FROM User u WHERE u.login = :name")
})
public class User extends SimpleAppEntity implements IUser {

    @Column(name="email", nullable=false)
    private String email;

    @Convert(converter = LocalDateTimeDbConverter.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "reg_date", nullable = false, updatable = false)
    protected LocalDateTime regDate;

    @Convert(converter = LocalDateTimeDbConverter.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "last_mod_date", nullable = false, updatable = false)
    protected LocalDateTime lastModifiedDate;

    @Column(name="password", nullable=false, length = 64)
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String passwordhash;

    @Column(nullable = false, length = 64)
    private String login;

    @Convert(converter = UserStatusCodeConverter.class)
    private UserStatusCode status = UserStatusCode.UNKNOWN;

    @Column(name = "default_lang")
    @Convert(converter = LanguageCodeConverter.class)
    private LanguageCode defaultLang = EnvConst.getDefaultLang();

    @Column(name = "i_su")
    private boolean isSuperUser;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    private void prePersist() {
        regDate = LocalDateTime.now();
        lastModifiedDate = regDate;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public UserStatusCode getStatus() {
        return status;
    }

    public void setStatus(UserStatusCode status) {
        this.status = status;
    }

    public LanguageCode getDefaultLang() {
        return defaultLang;
    }

    public void setDefaultLang(LanguageCode defaultLang) {
        this.defaultLang = defaultLang;
    }

    @Override
    public boolean isSuperUser() {
        return isSuperUser;
    }

}