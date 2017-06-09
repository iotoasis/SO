package com.pineone.icbms.so.util.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection Utiltity class.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 16..
 */
public class CollectionUtils {
    /**
     * cast List by TYPE.<BR/>
     *
     * @param srcList source list
     * @param clas    Class type to cast
     * @param <T>     Type
     * @return casted List
     */
    public static <T> List<T> castCollection(List srcList, Class<T> clas) {
        if (srcList != null) {
            List<T> list = new ArrayList<T>();
            for (Object obj : srcList) {
                if (obj != null && clas.isAssignableFrom(obj.getClass()))
                    list.add(clas.cast(obj));
            }
            return list;
        }
        else
            return null;
    }

    /**
     * list object to string.<BR/>
     *
     * @param srcList List object
     * @return String
     */
    public static String toStringWithLineFeed(List srcList) {
        StringBuffer sb = new StringBuffer();
        for(Object obj: srcList) {
            sb.append(obj.toString()).append('\n');
        }
        return sb.toString();
    }
}
