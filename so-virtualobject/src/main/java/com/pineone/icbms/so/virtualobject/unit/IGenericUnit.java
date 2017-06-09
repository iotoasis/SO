package com.pineone.icbms.so.virtualobject.unit;

import com.pineone.icbms.so.virtualobject.common.IUriOwner;

/**
 * Unit generic interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 27..
 */
public interface IGenericUnit extends IUnit, IUriOwner<String>
{
    /**
     * return URI.<BR/>
     * @return uri
     */
    String getUri();
}
