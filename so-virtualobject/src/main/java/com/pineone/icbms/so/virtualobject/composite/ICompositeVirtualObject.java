package com.pineone.icbms.so.virtualobject.composite;

import com.pineone.icbms.so.virtualobject.IVirtualObject;
import com.pineone.icbms.so.virtualobject.aspect.IAspect;
import com.pineone.icbms.so.virtualobject.functionlity.IFunctionality;

import java.util.List;

/**
 * Composite Virtual Object interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 17..
 */
public interface ICompositeVirtualObject<VIRTUALOBJECT extends IVirtualObject,
        FUNCTIONALITY extends IFunctionality, ASPECT extends IAspect> extends IVirtualObject<FUNCTIONALITY, ASPECT> {
    /**
     * return virtual object list.<BR/>
     * @return virtual object list
     */
    List<VIRTUALOBJECT> getVirtualObjectList();
}
