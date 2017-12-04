package com.pineone.icbms.so.virtualobject.orchestrationservice;

import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;

import java.util.List;

/**
 * Generic orchestrationservice interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IGenericOrchestrationService
        extends IOrchestrationService<IGenericCompositeVirtualObject, IGenericVirtualObject>, IGenericServiceEntity {
    /**
     * return orchestrationservice list.<BR/>
     *
     * @return orchestrationservice list
     */
    List<IGenericOrchestrationService> getOrchestrationServiceList();
}
