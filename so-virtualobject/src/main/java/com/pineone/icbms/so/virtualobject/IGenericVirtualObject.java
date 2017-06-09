package com.pineone.icbms.so.virtualobject;

import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.functionlity.IGenericFunctionality;

/**
 * Generic virtual object.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IGenericVirtualObject
        extends IVirtualObject<IGenericFunctionality, IGenericAspect>,
        IGenericServiceEntity {
    /**
     * return functionality.<BR/>
     *
     * @return functionality
     */
    IGenericFunctionality getFunctionality();

    /**
     * return aspect.<BR/>
     *
     * @return aspect
     */
    IGenericAspect getAspect();
}
