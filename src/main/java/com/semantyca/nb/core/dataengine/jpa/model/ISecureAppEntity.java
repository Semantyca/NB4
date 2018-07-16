package com.semantyca.nb.core.dataengine.jpa.model;

import com.semantyca.nb.core.dataengine.jpa.IAppEntity;
import com.semantyca.nb.core.dataengine.jpa.model.embedded.Reader;
import com.semantyca.nb.core.user.IUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISecureAppEntity extends IAppEntity {

    void addReader(IUser user);

    void resetEditors();

    void resetReadersEditors();

    void addReaderEditor(IUser user);

    void addReaderEditors(List<Long> substitutes);

    Map<Long, Reader> getReaders();

    Set<Long> getEditors();


}
