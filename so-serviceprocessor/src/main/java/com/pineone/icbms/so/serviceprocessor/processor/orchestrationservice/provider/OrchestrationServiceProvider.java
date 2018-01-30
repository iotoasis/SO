package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.provider;

import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.provider.IOrchestrationServiceProvider;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;

/**
 * OrchestrationService provider class.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class OrchestrationServiceProvider implements IOrchestrationServiceProvider {
    /**
     * Create
     *
     * @param iGenericOrchestrationService
     * @return ID
     */
    @Override
    public String create(IGenericOrchestrationService iGenericOrchestrationService) {
        return null;
    }

    /**
     * Retreive
     *
     * @param s
     * @return M
     */
    @Override
    public IGenericOrchestrationService retreive(String s) {
        return null;
    }

    /**
     * Update
     *
     * @param iGenericOrchestrationService
     * @return model ID
     */
    @Override
    public String update(IGenericOrchestrationService iGenericOrchestrationService) {
        return null;
    }

    /**
     * delete
     *
     * @param iGenericOrchestrationService
     * @return model ID
     */
    @Override
    public String delete(IGenericOrchestrationService iGenericOrchestrationService) {
        return null;
    }

    /**
     * return OrchestrationServiceForDB from database.<BR/>
     *
     * @param id orchestration service id
     * @return OrchestrationServiceForDB
     */
    public OrchestrationServiceForDB getOrchestrationServiceById(String id) {
        return DatabaseManager.getInstance().getOrchestrationServiceById(id);
    }
}
