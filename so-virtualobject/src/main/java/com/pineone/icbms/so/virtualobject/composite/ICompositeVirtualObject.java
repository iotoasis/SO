package com.pineone.icbms.so.virtualobject.composite;

import com.pineone.icbms.so.virtualobject.IVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IAspect;
import com.pineone.icbms.so.virtualobject.common.IVirtualEntity;
import com.pineone.icbms.so.virtualobject.function.IFunction;

import java.util.List;

/**
 * Composite Virtual Object interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 17..
 */
public interface ICompositeVirtualObject<VIRTUALOBJECT extends IVirtualObject> /*extends IVirtualObject<FUNCTIONALITY, ASPECT>*/ extends IVirtualEntity {
    /**
     * return virtual object list.<BR/>
     * @return virtual object list
     */
    List<VIRTUALOBJECT> getVirtualObjectList();
}
