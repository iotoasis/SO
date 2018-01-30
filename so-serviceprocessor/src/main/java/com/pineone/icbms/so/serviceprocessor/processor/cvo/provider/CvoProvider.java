package com.pineone.icbms.so.serviceprocessor.processor.cvo.provider;

import java.util.List;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.RuleBodyForDB;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.provider.ICompositeVirtualObjectProvider;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;

/**
 * OrchestrationService provider class.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class CvoProvider implements ICompositeVirtualObjectProvider {
    /**
     * Create
     *
     * @param IGenericCompositeVirtualObject
     * @return ID
     */
    @Override
    public String create(IGenericCompositeVirtualObject iGenericCompositeVirtualObject) {
        return null;
    }

    /**
     * Retreive
     *
     * @param s
     * @return M
     */
    @Override
    public IGenericCompositeVirtualObject retreive(String s) {
        return null;
    }

    /**
     * Update
     *
     * @param IGenericCompositeVirtualObject
     * @return model ID
     */
    @Override
    public String update(IGenericCompositeVirtualObject iGenericCompositeVirtualObject) {
        return null;
    }

    /**
     * delete
     *
     * @param IGenericCompositeVirtualObject
     * @return model ID
     */
    @Override
    public String delete(IGenericCompositeVirtualObject iGenericCompositeVirtualObject) {
        return null;
    }

    /**
     * return OrchestrationServiceForDB from database.<BR/>
     *
     * @param id orchestration service id
     * @return OrchestrationServiceForDB
     */
    public List<RuleBodyForDB> getRuleBodyListByOsId(String id) {
        return DatabaseManager.getInstance().getRuleBodyListByOsId(id);
    }
}
