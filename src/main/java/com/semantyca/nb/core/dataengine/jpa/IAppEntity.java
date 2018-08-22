package com.semantyca.nb.core.dataengine.jpa;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAppEntity extends ISimpleAppEntity<UUID> {

    void setAuthor(Long author);

    Long getAuthor();

    LocalDateTime getRegDate();

    boolean isEditable();

    void setEditable(boolean isEditable);

    default boolean wasRead(){
        return true;
    }

    String getTitle();

    void setTitle(String title);

    default String getType() {
        return this.getClass().getSimpleName();
    }

    void setRegDate(LocalDateTime regDate);

    void setLastModifiedDate(LocalDateTime lastModifiedDate);

    void setLastModifier(Long lastModifier);

    default String getIdentifier() {
        UUID id = getId();
        if (id != null) {
            return getId().toString();
        } else {
            return "undefined";
        }
    }

}
