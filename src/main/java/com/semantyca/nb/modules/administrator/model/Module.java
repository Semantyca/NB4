package com.semantyca.nb.modules.administrator.model;

import com.semantyca.nb.core.dataengine.jpa.model.SimpleReferenceEntity;

import javax.persistence.*;

@Entity
@Table(name = "_modules", uniqueConstraints = @UniqueConstraint(columnNames = {"identifier"}))
@NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module AS m ORDER BY m.regDate")
public class Module extends SimpleReferenceEntity {

    @Column(name = "is_on")
    private boolean isOn;

    @Column(name = "icon_url")
    private String iconUrl;


    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        boolean isValid = iconUrl != null && !iconUrl.trim().isEmpty() && !iconUrl.contains("/api/");

        if (isValid) {
            this.iconUrl = iconUrl.trim();
        } else {
            this.iconUrl = null;
        }
    }


}
