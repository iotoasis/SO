package com.pineone.icbms.so.virtualobject.orchestrationservice;

import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.common.IVirtualEntity;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;

import java.util.List;

/**
 * Orchestration Service interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IOrchestrationService<CVO extends IGenericCompositeVirtualObject, VO extends IGenericVirtualObject> extends IVirtualEntity {
    /**
     * return virtual object.<BR/>
     *
     * @return virtual object
     */
    List<CVO> getCompositeVirtualObjectList();
    List<VO> getVirtualObjectList();
}
