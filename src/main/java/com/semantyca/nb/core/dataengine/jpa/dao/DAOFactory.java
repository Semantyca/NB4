package com.semantyca.nb.core.dataengine.jpa.dao;

import com.semantyca.nb.core.dataengine.jpa.IDAO;
import com.semantyca.nb.logger.Lg;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DAOFactory {
    private static Map<String, IDAO> daos = new HashMap<String, IDAO>();

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static IDAO get(String entityAnyAlias) {
        String instanceKey = entityAnyAlias;
        if (daos.containsKey(instanceKey)) {
            return daos.get(instanceKey);
        } else {
            try {
                Class<?> clazz = Class.forName(entityAnyAlias + "DAO");
                Constructor<?> contructor = clazz.getConstructor();
                IDAO dao = (IDAO<?, UUID>) contructor.newInstance();
                daos.put(instanceKey, dao);
                return dao;
            } catch (ClassCastException e) {
                Lg.error("DAO for \"" + entityAnyAlias + "\"  has been not found");
            } catch (ClassNotFoundException e) {
                Lg.error("DAO for \"" + entityAnyAlias + "\"  has been not found");
            } catch (InstantiationException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | NoSuchMethodException | InvocationTargetException e) {
                Lg.exception(e);
            } catch (Exception e) {
                Lg.exception(e);
            }
        }
        return null;
    }

    public static IDAO get(Class entityClazz) {
        return get(entityClazz.getCanonicalName());
    }

}
