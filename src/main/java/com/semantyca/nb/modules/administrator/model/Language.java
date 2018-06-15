package com.semantyca.nb.modules.administrator.model;


import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;
import com.semantyca.nb.localization.constants.LanguageCode;
import com.semantyca.nb.modules.administrator.init.ModuleConst;

import javax.persistence.*;

@Entity
@Table(name = "_langs")
@NamedQuery(name = "Language.findAll", query = "SELECT m FROM Language AS m ORDER BY m.regDate")
public class Language extends SimpleReferenceEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 7, unique = true)
    private LanguageCode code = LanguageCode.UNKNOWN;

    @Column(name = "is_on")
    private boolean isOn;

    private int position;


    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public LanguageCode getCode() {
        return code;
    }

    public void setCode(LanguageCode code) {
        this.code = code;
    }

    public void setLanguageCode(String id) {
        this.code = LanguageCode.valueOf(id);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getURL() {
        return ModuleConst.BASE_URL + "languages/" + getId();
    }
}
