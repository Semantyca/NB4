package com.semantyca.nb.core.dataengine.jpa.model;


import com.semantyca.nb.core.dataengine.jpa.model.embedded.Reader;
import com.semantyca.nb.core.user.IUser;

import javax.persistence.*;
import java.util.*;

//@Customizer(NamingCustomizer.class)
@MappedSuperclass
public abstract class SecureAppEntity<K> extends AppEntity implements ISecureAppEntity {

    @ElementCollection
    private Set<Long> editors = new HashSet<>();

    @ElementCollection
    @MapKey(name = "reader")
    @CollectionTable(joinColumns = @JoinColumn(name = "entity_id"), uniqueConstraints = @UniqueConstraint(columnNames = {"entity_id", "readers"}))
    private Map<Long, Reader> readers = new HashMap<>();

    public Set<Long> getEditors() {
        return editors;
    }

    public void setEditors(Set<Long> editors) {
        this.editors = editors;
    }

    public void addReaderEditor(IUser user) {
        long id = user.getId();
        addReaderEditor(id);
    }

    public void addReaderEditors(List<Long> users) {
        for (Long reader : users) {
            addReader(reader);
        }
        addEditors(users);
    }

    public void addReaderEditor(Long userId) {
        if (userId != 0) {
            editors.add(userId);
            addReader(userId);
        }
    }

    public void addReadersList(List<Long> readers) {
        for (Long reader : readers) {
            addReader(reader);
        }
    }

    public void addReadersList(Set<Long> readers) {
        for (Long reader : readers) {
            addReader(reader);
        }
    }

    public Map<Long, Reader> getReaders() {
        return readers;
    }


    public void setReaders(Map<Long, Reader> readers) {
        this.readers = readers;
    }

    public void setReaders(Set<Long> r) {
        readers.clear();
        for (Long reader : r) {
            addReader(reader);
        }
    }

    public void addReader(IUser user) {
        long id = user.getId();
        addReader(id);
    }

    public void withdrawEditor(IUser user) {
        long id = user.getId();
        editors.remove(id);
        addReader(id);
    }

    public void addReader(Long userId) {
        if (userId > 0 && !readers.containsKey(userId)) {
            Reader reader = new Reader();
            reader.setReader(userId);
            readers.put(userId, reader);
        }
    }



    public void addReaders(Map<Long, Reader> r) {
        for (Map.Entry<Long, Reader> reader : r.entrySet()) {
            addReader(reader.getKey());
        }
    }

    public void addEditors(List<Long> r) {
        addReadersList(r);
        editors.addAll(r);
    }

    public void resetReadersEditors() {
        editors.clear();
        readers.clear();
    }

    public void resetEditors() {
        editors.clear();
    }

    @Override
    public boolean isEditable() {
        return super.isEditable();
    }
}
