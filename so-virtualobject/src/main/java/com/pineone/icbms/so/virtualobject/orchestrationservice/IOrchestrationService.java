package com.pineone.icbms.so.virtualobject.orchestrationservice;

import com.pineone.icbms.so.virtualobject.IVirtualObject;
import com.pineone.icbms.so.virtualobject.common.IVirtualEntity;

import java.util.List;

/**
 * Orchestration Service interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
public interface IOrchestrationService<VO extends IVirtualObject> extends IVirtualEntity {
    /**
     * return virtual object.<BR/>
     *
     * @return virtual object
     */
    List<VO> getVirtualObjectList();
}
