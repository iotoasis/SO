package com.pineone.icbms.so.virtualobject.profile;

import com.pineone.icbms.so.virtualobject.common.IGenericIdNameOwner;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IContextModel;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IOrchestrationService;

/**
 * Profile interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 17..
 */
public interface IProfile<CONTEXTMODEL extends IContextModel, ORCHESTRATIONSERVICE extends IOrchestrationService>
        extends IGenericIdNameOwner {
    /**
     * return context model.<BR/>
     *
     * @return context model
     */
    CONTEXTMODEL getContextModel();

    /**
     * return orchestration serviceprocessor.<BR/>
     *
     * @return orchestration serviceprocessor
     */
    ORCHESTRATIONSERVICE getOrchestrationService();
}
