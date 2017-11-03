package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.provider;

import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.provider.IVirtualObjectProvider;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;

/**
 * VirtualObject provider class.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class VirtualObjectProvider implements IVirtualObjectProvider {
    /**
     * Create
     *
     * @param iGenericVirtualObject
     * @return ID
     */
    @Override
    public String create(IGenericVirtualObject iGenericVirtualObject) {
        return null;
    }

    /**
     * Retrecive
     *
     * @param s
     * @return M
     */
    @Override
    public IGenericVirtualObject retreive(String s) {
        return null;
    }

    /**
     * Update
     *
     * @param iGenericVirtualObject
     * @return model ID
     */
    @Override
    public String update(IGenericVirtualObject iGenericVirtualObject) {
        return null;
    }

    /**
     * delete
     *
     * @param iGenericVirtualObject
     * @return model ID
     */
    @Override
    public String delete(IGenericVirtualObject iGenericVirtualObject) {
        return null;
    }

    /**
     * return VirtualObjectForDB from database.<BR/>
     *
     * @param id virtual object id
     * @return VirtualObjectForDB
     */
    public VirtualObjectForDB getVirtualObjectById(String id) {
        return DatabaseManager.getInstance().getVirtualObjectById(id);
    }
}
