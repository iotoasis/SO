package com.pineone.icbms.so.virtualobject;

import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;

/**
 * Generic virtual object.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IGenericVirtualObject
        extends IVirtualObject<IGenericFunction, IGenericAspect>,
        IGenericServiceEntity {
    /**
     * return function.<BR/>
     *
     * @return function
     */
    IGenericFunction getFunction();

    /**
     * return aspect.<BR/>
     *
     * @return aspect
     */
    IGenericAspect getAspect();
}
