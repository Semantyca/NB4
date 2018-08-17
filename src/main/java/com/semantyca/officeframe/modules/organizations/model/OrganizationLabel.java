package com.semantyca.officeframe.modules.organizations.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalizedValConverter;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.organizations.init.ModuleConst;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = ModuleConst.CODE + "__orglabels", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class OrganizationLabel extends SimpleReferenceEntity {

    @ManyToMany(mappedBy = "labels")
    private List<Organization> labels;

    @Convert(converter = LocalizedValConverter.class)
    @Column(name = "localized_descr", columnDefinition = "jsonb")
    private Map<LanguageCode, String> localizedDescr;

    public List<Organization> getLabels() {
        return labels;
    }

    public Map<LanguageCode, String> getLocalizedDescr() {
        return localizedDescr;
    }

    public void setLocalizedDescr(Map<LanguageCode, String> localizedDescr) {
        this.localizedDescr = localizedDescr;
    }


}
