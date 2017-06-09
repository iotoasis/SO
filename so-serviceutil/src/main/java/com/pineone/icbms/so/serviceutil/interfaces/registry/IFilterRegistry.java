package com.pineone.icbms.so.serviceutil.interfaces.registry;

import com.pineone.icbms.so.serviceutil.interfaces.filter.IGenericFilter;
import com.pineone.icbms.so.util.registry.IGenericKeyValueRegistry;

/**
 * filter registry interface.<BR/>
 *
 * Created by uni4love on 2016. 12. 23..
 */
public interface IFilterRegistry<K, V> extends IGenericKeyValueRegistry<K, V> {
    /**
     * add a filter.<BR/>
     *
     * @param filter
     */
    public void addFilter(IGenericFilter filter);

    /**
     * return existence of filter<BR/>
     *
     * @param filter filter
     * @return true/false
     */
    public boolean existFilter(IGenericFilter filter);
}
