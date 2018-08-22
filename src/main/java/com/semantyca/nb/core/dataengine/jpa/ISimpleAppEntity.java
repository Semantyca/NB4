package com.semantyca.nb.core.dataengine.jpa;

import com.semantyca.nb.core.dataengine.jpa.model.constant.ExistenceState;

public interface ISimpleAppEntity<K>  {

    K getId();

    default void setExistenceState(ExistenceState existenceState) {

    }

    default ExistenceState getExistenceState() {
        return ExistenceState.UNKNOWN;
    }

}
