package com.pineone.icbms.so.serviceutil.interfaces.filter;

/**
 * filter interface.<BR/>
 *
 * Created by uni4love on 2016. 12. 23..
 */
public interface IFilter<V> {
    /**
     * filtering
     * @param v target for filtering
     * @return filtered target
     */
    V filtering(V v);
}
