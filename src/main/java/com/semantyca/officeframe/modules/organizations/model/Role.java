package com.semantyca.officeframe.modules.organizations.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.officeframe.modules.organizations.init.ModuleConst;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Map;

@Entity
@Table(name = ModuleConst.CODE + "__roles", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Role extends SimpleReferenceEntity {


    //  @Convert(converter = LocalizedValConverter.class)
    @Column(name = "localized_descr", columnDefinition = "jsonb")
    private Map<LanguageCode, String> localizedDescr;

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
