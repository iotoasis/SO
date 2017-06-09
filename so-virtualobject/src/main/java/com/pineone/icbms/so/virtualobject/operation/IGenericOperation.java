package com.pineone.icbms.so.virtualobject.operation;

import com.pineone.icbms.so.virtualobject.common.IUriOwner;

/**
 * Generic location interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 27..
 */
public interface IGenericOperation extends IOperation, IUriOwner<String>
{
    /**
     * return URI.<BR/>
     * @return uri
     */
    String getUri();
}
