package com.semantyca.nb.core.rest;

import com.semantyca.nb.core.env.EnvConst;
import com.semantyca.nb.ui.view.SortParams;
import com.semantyca.nb.util.StringUtil;

import java.util.Iterator;
import java.util.Map;

public class WebFormData {
    private Map<String, String[]> data;

    public WebFormData(Map<String, String[]> formData) {
        this.data = formData;
    }

    public String getStringValueSilently(String fn, String defaultValue) {
        try {
            String value[] = data.get(fn);
            String val = value[0].trim();
            if (val.isEmpty()) {
                return defaultValue;
            } else {
                return val;
            }
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getStringValueSilently(String fn) {
        try {
            String value[] = data.get(fn);
            if (value[0].equalsIgnoreCase("null")) {
                return "";
            } else {
                Object r = value[0].trim();
                return (String) r;
            }
        } catch (Exception e) {
            return "";
        }
    }

    public int getNumberValueSilently(String fn, int defaultValue) {
        if (!containsField(fn)) {
            return defaultValue;
        }

        try {
            String value[] = data.get(fn);
            return Integer.parseInt(value[0].trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public Integer[] getListOfNumberValues(String fn, int defaultValue) {
        try {
            String value[] = data.get(fn);
            Integer[] nValue = new Integer[value.length];
            for (int i = 0; i < value.length; i++) {
                try {
                    nValue[i] = Integer.parseInt(value[i].trim());
                } catch (Exception e) {
                    nValue[i] = defaultValue;
                }
            }
            return nValue;
        } catch (Exception e) {
            return new Integer[]{defaultValue};
        }
    }

    public SortParams getSortParams(SortParams defaultSort) {
        return SortParams.valueOf(getStringValueSilently("sort"), defaultSort);
    }

    public boolean containsField(String key) {
        return data.containsKey(key);
    }

    @Override
    public String toString() {
        String result = "";
        Iterator<String> en = data.keySet().iterator();
        while (en.hasNext()) {
            String webFormFieldName = en.next();
            String[] val = data.get(webFormFieldName);
            String v = "";
            for (int i = 0; i < val.length; i++) {
                v += val[i] + "[" + Integer.toString(i) + "],";
            }
            result += " " + webFormFieldName + " = " + v + "\n";
        }
        return result;
    }


    public String getFormSesId() {
        String fsId = getStringValueSilently(EnvConst.FSID_FIELD_NAME, StringUtil.getRndText());
        if (fsId.isEmpty()) {
            fsId = StringUtil.getRndText();
        }
        return fsId;
    }

    public int getPage() {
        return getNumberValueSilently("page", 1);
    }


    public int getPageSize() {
        return getNumberValueSilently("pagesize", EnvConst.DEFAULT_PAGE_SIZE);
    }
}
