package com.pineone.icbms.so.util.property;

import java.util.*;

/**
 * Property Utility class.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public class PropertyUtils {

    /**
     * return Properties from Map.<BR/>
     *
     * @param propertiesMap Map
     * @return Properties
     */
    public static Properties getProperties(Map propertiesMap) {
        Properties properties = new Properties();
        properties.putAll(propertiesMap);
        return properties;
    }

    /**
     * return Map<String, String> from Properties.<BR/>
     *
     * @param properties Properties
     * @return Map<String, String>
     */
    public static Map<String, String> getMapFromProperties(Properties properties) {
        HashMap<String, String> hm = new HashMap<String, String>();
        for (String key : properties.stringPropertyNames())
        {
            hm.put(key, properties.getProperty(key));
        }
        return hm;
    }

}
