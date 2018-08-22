package com.semantyca.nb.core.dataengine.jpa.model.convertor.db.eclipselink;

import org.postgresql.util.PGobject;

public class PostgresUuid extends PGobject implements Comparable<Object> {
    private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(Object arg0) {
        return 0;
    }
}