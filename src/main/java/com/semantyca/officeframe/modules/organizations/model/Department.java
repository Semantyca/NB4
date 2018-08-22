package com.semantyca.officeframe.modules.organizations.model;


import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.officeframe.modules.organizations.init.ModuleConst;
import com.semantyca.officeframe.modules.organizations.model.converter.DepartmentConverter;
import com.semantyca.officeframe.modules.organizations.model.converter.EmployeeConverter;
import com.semantyca.officeframe.modules.reference.model.DepartmentType;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Converters;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = ModuleConst.CODE + "__departments", uniqueConstraints = @UniqueConstraint(columnNames = {"identifier", "organization_id"}))
@Converters({@Converter(name = "dep_conv", converterClass = DepartmentConverter.class),
        @Converter(name = "emp_conv", converterClass = EmployeeConverter.class)})
public class Department extends SimpleReferenceEntity {

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private DepartmentType departmentType;

    @NotNull
    @ManyToOne(optional = true)
    @JoinColumn(nullable = false)
    private Organization organization;

    @Convert("dep_conv")
    @Basic(fetch = FetchType.LAZY, optional = true)
    private Department leadDepartment;

    @Convert("emp_conv")
    @Basic(fetch = FetchType.LAZY, optional = true)
    private Employee boss;

    private int rank = 999;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Department getLeadDepartment() {
        return leadDepartment;
    }

    public void setLeadDepartment(Department leadDepartment) {
        this.leadDepartment = leadDepartment;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


}
