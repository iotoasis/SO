package com.pineone.icbms.so.virtualobject;

import com.pineone.icbms.so.virtualobject.common.IVirtualEntity;

/**
 * Virtual Object interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IVirtualObject<FUNCTIONALITY, ASPECT> extends IVirtualEntity {
    /**
     * return function.<BR/>
     *
     * @return function
     */
    FUNCTIONALITY getFunction();

    /**
     * return aspect.<BR/>
     *
     * @return aspect
     */
    ASPECT getAspect();
}
