package com.pineone.icbms.so.virtualobject.function;

import com.pineone.icbms.so.virtualobject.common.IUriOwner;

/**
 * Generic Function interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 27..
 */
public interface IGenericFunction extends IFunction, IUriOwner<String> {
    /**
     * return URI.<BR/>
     * @return uri
     */
    String getUri();
}
