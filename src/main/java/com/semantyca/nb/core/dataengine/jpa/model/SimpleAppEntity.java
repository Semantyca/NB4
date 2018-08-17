package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.ISimpleAppEntity;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;

@MappedSuperclass
public abstract class SimpleAppEntity implements ISimpleAppEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @JohnzonIgnore
    private Long id;

    @JohnzonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
