package com.semantyca.officeframe.modules.reference.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.officeframe.modules.reference.init.ModuleConst;
import com.semantyca.officeframe.modules.reference.model.constants.CountryCode;

import javax.persistence.*;

@Entity
@Cacheable(true)
@Table(name = ModuleConst.CODE + "__countries", uniqueConstraints = @UniqueConstraint(columnNames = {"identifier", "code"}))
public class Country extends SimpleReferenceEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7, unique = true)
    private CountryCode code = CountryCode.UNKNOWN;


    public CountryCode getCode() {
        return code;
    }

    public void setCode(CountryCode code) {
        this.code = code;
    }

}
