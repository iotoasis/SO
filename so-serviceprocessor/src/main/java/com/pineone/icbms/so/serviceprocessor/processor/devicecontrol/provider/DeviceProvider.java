package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.provider;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;
import com.pineone.icbms.so.serviceprocessor.repository.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.provider.IDeviceControlProvider;

import java.util.List;

/**
 * Device provider implements.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class DeviceProvider implements IDeviceControlProvider {
    /**
     * database manager
     */
    DatabaseManager databaseManager;

    /**
     * constructor.<BR/>
     *
     * @param databaseManager DatabaseManager
     */
    public DeviceProvider(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Create
     *
     * @param iGenericVirtualDevice
     * @return ID
     */
    @Override
    public String create(IGenericVirtualDevice iGenericVirtualDevice) {
        return null;
    }

    /**
     * Retrecive
     *
     * @param s
     * @return M
     */
    @Override
    public IGenericVirtualDevice retreive(String s) {
        return null;
    }

    /**
     * Update
     *
     * @param iGenericVirtualDevice
     * @return model ID
     */
    @Override
    public String update(IGenericVirtualDevice iGenericVirtualDevice) {
        return null;
    }

    /**
     * delete
     *
     * @param iGenericVirtualDevice
     * @return model ID
     */
    @Override
    public String delete(IGenericVirtualDevice iGenericVirtualDevice) {
        return null;
    }

    /**
     * return IGenericVirtualDevice list from database.<BR/>
     *
     * @param function function uri
     * @param aspect        aspect
     * @param locationUri   location uri
     * @return IGenericVirtualDevice list
     */
    public List<DeviceForDB> getDeviceList(String function, String aspect, String locationUri) {
        return databaseManager.getDeviceList(function, aspect, locationUri);
    }
}
