package com.pineone.icbms.so.virtualobject.profile;

import com.pineone.icbms.so.virtualobject.common.IGenericServiceEntity;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;

/**
 * Generic profile interface.<BR/>
 * Created by uni4love on 2016. 11. 17..
 */
public interface IGenericProfile extends IProfile<IGenericContextModel, IGenericOrchestrationService>
        , IGenericServiceEntity {

    /**
     * return context model.<BR/>
     *
     * @return context model
     */
    IGenericContextModel getContextModel();

    /**
     * return orchestration serviceprocessor.<BR/>
     *
     * @return orchestration serviceprocessor
     */
    IGenericOrchestrationService getOrchestrationService();

    /**
     * return location.<BR/>
     * @return location
     */
    IGenericLocation getLocation();
}
