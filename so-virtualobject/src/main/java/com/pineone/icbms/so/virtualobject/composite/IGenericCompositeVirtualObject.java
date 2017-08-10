package com.pineone.icbms.so.virtualobject.composite;

import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IGenericAspect;
import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.function.IGenericFunction;

import java.util.List;

/**
 * Composite Virtual Object generic interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 17..
 */
public interface IGenericCompositeVirtualObject
        extends ICompositeVirtualObject<IGenericVirtualObject, IGenericFunction, IGenericAspect>, IGenericServiceEntity {
    /**
     * return virtual object list.<BR/>
     *
     * @return virtual object list
     */
    List<IGenericVirtualObject> getVirtualObjectList();

    /**
     * add a Virtual Object.<BR/>
     *
     * @param virtualObject virtual object
     */
    void addVirtualObject(IGenericVirtualObject virtualObject);

    /**
     * return current vo tree depth.<BR/>
     *
     * @return depth
     */
    int getCurrentDepth();

    /**
     * set current vo tree depth.<BR/>
     *
     * @param depth depth
     */
    void setCurrentDepth(int depth);

}
