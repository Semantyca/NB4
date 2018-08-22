package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.ISimpleAppEntity;

import javax.persistence.*;

@MappedSuperclass
public abstract class SimpleAppEntity implements ISimpleAppEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }


}
