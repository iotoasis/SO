package com.pineone.icbms.so.virtualobject.location;

import com.pineone.icbms.so.virtualobject.common.IUriOwner;

/**
 * Generic location interface.<BR/>
 * Created by uni4love on 2016. 11. 27..
 */
public interface IGenericLocation extends ILocation, IUriOwner<String>
{
    /**
     * return URI.<BR/>
     * @return uri
     */
    String getUri();
}
