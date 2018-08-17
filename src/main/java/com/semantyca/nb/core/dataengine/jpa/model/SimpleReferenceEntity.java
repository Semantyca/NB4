package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.model.convertor.db.LocalizedValConverter;
import com.semantyca.nb.localization.constants.LanguageCode;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public class SimpleReferenceEntity extends AppEntity{

    @Column(length = 128, nullable = false)
    private String identifier;

    @Convert(converter = LocalizedValConverter.class)
    @Column(name = "loc_name", columnDefinition = "jsonb")
    private Map<LanguageCode, String> locName = new HashMap<LanguageCode, String>();


    @Override
    public String getTitle() {
        if (super.getTitle() == null || super.getTitle().isEmpty()) {
            return identifier;
        }
        return super.getTitle();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        super.setTitle(identifier);
    }


    public Map<LanguageCode, String> getLocName() {
        return locName;
    }

    public void setLocName(Map<LanguageCode, String> locName) {
        this.locName = locName;
    }

    public String getLocName(LanguageCode lang) {
        try {
            String val = locName.get(lang);
            if (val != null && (!val.isEmpty())) {
                return val;
            } else {
                return identifier;
            }
        } catch (Exception e) {
            return identifier;
        }
    }

    public void setLocName(String val, LanguageCode languageCode) {
        this.locName.put(languageCode, val);
    }


}
