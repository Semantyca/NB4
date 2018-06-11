package com.semantyca.nb.ui.view;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by medin on 27.09.16.
 */
public class SortParams {

    private Map<String, _Direction> sort = new LinkedHashMap<>();

    public static SortParams asc(String fieldName) {
        SortParams sortMap = new SortParams();
        sortMap.addAsc(fieldName);
        return sortMap;
    }

    public static SortParams desc(String fieldName) {
        SortParams sortMap = new SortParams();
        sortMap.addDesc(fieldName);
        return sortMap;
    }

    public static SortParams valueOf(String sort, SortParams defaultSort) {
        SortParams result = valueOf(sort);
        if (result == null) {
            return defaultSort;
        }

        return result;
    }

    public static SortParams valueOf(String sort) {
        if (sort == null || sort.trim().isEmpty()) {
            return null;
        }

        String[] sortParams = sort.split(","); // title,-regDate

        if (sortParams.length == 0) {
            return null;
        }

        SortParams result = new SortParams();

        for (String param : sortParams) {
            if (!param.isEmpty()) {
                String name;
                boolean isAscending;
                if (param.charAt(0) == '-') { // descending sort prefix (minus)
                    isAscending = false;
                    name = param.substring(1);
                } else {
                    name = param;
                    isAscending = true;
                }
                if (isAscending) {
                    result.addAsc(name);
                } else {
                    result.addDesc(name);
                }
            }
        }

        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public SortParams addAsc(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("fieldName can not be empty");
        }

        sort.put(fieldName, new _Direction(true));
        return this;
    }

    public SortParams addDesc(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("fieldName can not be empty");
        }

        sort.put(fieldName, new _Direction(false));
        return this;
    }

    public final Map<String, _Direction> values() {
        return sort;
    }

    public boolean isEmpty() {
        return sort.isEmpty();
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<String, _Direction> es : sort.entrySet()) {
            s += es.getKey() + ":" + es.getValue().isAscending() + ", ";
        }
        return s;
    }

    public class _Direction {

        private boolean ascending;

        _Direction(boolean isAscending) {
            ascending = isAscending;
        }

        public boolean isAscending() {
            return ascending;
        }
    }
}
