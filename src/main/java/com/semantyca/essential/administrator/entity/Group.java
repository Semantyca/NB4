package com.semantyca.essential.administrator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_groups")
public class Group {

    public static final String USERS_GROUP = "users";

    @Id
    @Column(name="email", nullable=false, length=255)
    private String email;

    @Column(name="groupname", nullable=false, length=32)
    private String groupname;

    public Group() {}

    public Group(String email, String groupname) {
        this.email = email;
        this.groupname = groupname;
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
