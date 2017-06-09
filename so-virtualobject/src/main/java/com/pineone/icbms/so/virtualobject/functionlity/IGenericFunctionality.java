package com.pineone.icbms.so.virtualobject.functionlity;

import com.pineone.icbms.so.virtualobject.common.IUriOwner;

/**
 * Generic Functionality interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 27..
 */
public interface IGenericFunctionality extends IFunctionality, IUriOwner<String> {
    /**
     * return URI.<BR/>
     * @return uri
     */
    String getUri();
}
