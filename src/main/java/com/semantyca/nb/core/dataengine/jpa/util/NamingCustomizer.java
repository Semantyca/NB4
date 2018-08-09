package com.semantyca.nb.core.dataengine.jpa.util;
/*
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.AggregateCollectionMapping;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectCollectionMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class NamingCustomizer implements DescriptorCustomizer {
    private static Map<String, String> apps = new HashMap<String, String>();

    public static void addAppCode(String appPackage, String code) {
        apps.put(appPackage, code);
    }

    @Override
    public void customize(ClassDescriptor classDescriptor) throws Exception {
        for (DatabaseMapping mapping : classDescriptor.getMappings()) {
            String prefix = getPrefix(classDescriptor);
            if (mapping.isAggregateCollectionMapping()){
                AggregateCollectionMapping agregateCollectionMapping = (AggregateCollectionMapping) mapping;
              //   System.out.println("agregate=" + agregateCollectionMapping.getAttributeName());
                if (agregateCollectionMapping.getAttributeName().equalsIgnoreCase("readers")) {
                  //  System.out.println(mapping.getClass().getCanonicalName());
                    String referenceTableName = classDescriptor.getAlias().toLowerCase() + "_readers";
                    Vector keys =  agregateCollectionMapping.getTargetForeignKeyFields();
                    DatabaseField field = (DatabaseField) keys.get(0);
                    field.getTable().setName(getRefTableName(referenceTableName, prefix));

                }else if(agregateCollectionMapping.getAttributeName().equalsIgnoreCase("altname")){
                    String referenceTableName = classDescriptor.getAlias().toLowerCase() + "_altname";
                    Vector keys =  agregateCollectionMapping.getTargetForeignKeyFields();
                    DatabaseField field = (DatabaseField) keys.get(0);
                    field.getTable().setName(getRefTableName(referenceTableName, prefix));
                }
            }else if (mapping.isDirectCollectionMapping()) {
                DirectCollectionMapping collectionMapping = (DirectCollectionMapping) mapping;
             //   System.out.println("not agregate=" + collectionMapping.getAttributeName());
                String referenceTableName = collectionMapping.getReferenceTableName();
                collectionMapping.getDirectField().getTable().setName(getRefTableName(referenceTableName, prefix));
            }
        }
    }

    private String getPrefix(ClassDescriptor classDescriptor){
        String packName = classDescriptor.getJavaClass().getPackage().getName();
        return apps.get(packName.substring(0, packName.indexOf(".")));
    }

    private String getRefTableName(String referenceTableName, String prefix){
        if (prefix != null) {
            return prefix + "__" + referenceTableName;
        } else {
            return "_" + referenceTableName;
        }
    }
}*/