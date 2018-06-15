package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.ISimpleAppEntity;

import javax.persistence.*;

@MappedSuperclass
public abstract class SimpleAppEntity implements ISimpleAppEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }



    public String getType() {
        return getClass().getSimpleName();
    }
}
