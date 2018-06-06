package com.semantyca.essential.administrator.entity;

import javax.persistence.*;

@Entity
@Table(name="_users")
@NamedQueries({
        @NamedQuery(name = "findById", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User{
    @Id
    @Column(name="email", nullable=false)
    private String email;

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
}