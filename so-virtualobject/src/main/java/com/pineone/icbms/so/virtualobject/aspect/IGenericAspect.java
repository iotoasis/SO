package com.pineone.icbms.so.virtualobject.aspect;

import com.pineone.icbms.so.virtualobject.common.IUriOwner;

/**
 * Generic Aspect interface.<BR/>
 * Created by uni4love on 2016. 11. 27..
 */
public interface IGenericAspect extends IAspect, IUriOwner<String>
{
    /**
     * return URI.<BR/>
     * @return uri
     */
    String getUri();
}
