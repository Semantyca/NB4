package com.semantyca.nb.core.dataengine.jpa;

import com.semantyca.nb.modules.administrator.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAppEntity extends ISimpleAppEntity<UUID> {

    Long getAuthor();

    void setLastModifier(User user);

    LocalDateTime getRegDate();

    boolean isEditable();

    void setEditable(boolean isEditable);

    default boolean wasRead(){
        return true;
    }

    String getTitle();

    String getURL();

    void setTitle(String title);

    default String getType() {
        return this.getClass().getSimpleName();
    }

    boolean isNew();


}
