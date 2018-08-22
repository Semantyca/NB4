package com.semantyca.nb.core.dataengine.jpa;

import com.semantyca.nb.core.dataengine.jpa.model.EntityAttachment;

import java.util.List;

public interface IEntityWithAttachments {

    List<EntityAttachment> getFiles();

    void setFiles(List<EntityAttachment> attachmentMap);
}
