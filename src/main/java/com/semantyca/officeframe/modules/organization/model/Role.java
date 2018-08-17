package com.semantyca.officeframe.modules.organization.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalizedValConverter;
import com.semantyca.nb.localization.constants.LanguageCode;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "staff__roles", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "SELECT m FROM staff.model.Role AS m ORDER BY m.regDate"),
        @NamedQuery(name = "Role.firedRole", query = "SELECT m FROM staff.model.Role AS m WHERE m.name='fired'")
})
public class Role extends SimpleReferenceEntity {

    @ManyToMany(mappedBy = "roles")
    private List<Employee> employees;

    @Convert(converter = LocalizedValConverter.class)
    @Column(name = "localized_descr", columnDefinition = "jsonb")
    private Map<LanguageCode, String> localizedDescr;

    public List<Employee> getEmployees() {
        return employees;
    }

    public Map<LanguageCode, String> getLocalizedDescr() {
        return localizedDescr;
    }

    public void setLocalizedDescr(Map<LanguageCode, String> localizedDescr) {
        this.localizedDescr = localizedDescr;
    }


    public String getName() {
        return getIdentifier();
    }
}
