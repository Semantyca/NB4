package com.semantyca.officeframe.modules.organizations.model;


import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.nb.core.user.IUser;
import com.semantyca.nb.modules.administrator.model.User;
import com.semantyca.officeframe.modules.organizations.init.ModuleConst;
import com.semantyca.officeframe.modules.organizations.model.constants.RoleType;
import com.semantyca.officeframe.modules.reference.model.Position;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.Convert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = ModuleConst.CODE + "__employees", uniqueConstraints = {@UniqueConstraint(columnNames = {"identifier", "organization_id"}),
        @UniqueConstraint(columnNames = {"user_id", "organization_id"})})
@Cache(refreshOnlyIfNewer = true)
public class Employee extends SimpleReferenceEntity {

    @OneToOne(cascade = {CascadeType.DETACH}, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "birth_date")
    private Date birthDate;

    private String iin = "";

    private String phone;

    @NotNull
    @ManyToOne(optional = true)
    @JoinColumn(nullable = false)
    private Organization organization;

    @Convert("dep_conv")
    @Basic(fetch = FetchType.LAZY, optional = true)
    private Department department;

    @Convert("emp_conv")
    @Basic(fetch = FetchType.LAZY, optional = true)
    private Employee boss;

    @ManyToOne(optional = true)
    @JoinColumn(nullable = false)
    private Position position;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = ModuleConst.CODE + "__employee_role")
    private List<Role> roles;

    private int rank = 999;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role r) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(r);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public List<String> getAllRoles() {
        List<String> list = new ArrayList<>();
        if (roles == null) {
            return list;
        }

        for (Role r : roles) {
            list.add(r.getName());
        }
        return list;
    }

    public boolean hasRole(String roleName) {
        if (roles == null) {
            return false;
        }

        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }


    public boolean isFired() {
        if (roles == null) {
            return false;
        }

        for (Role role : roles) {
            if (role.getName().equals(RoleType.FIRED)) {
                return true;
            }
        }
        return false;
    }

}
