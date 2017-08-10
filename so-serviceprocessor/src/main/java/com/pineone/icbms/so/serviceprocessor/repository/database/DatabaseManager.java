package com.pineone.icbms.so.serviceprocessor.repository.database;

import com.pineone.icbms.so.interfaces.database.model.*;
import com.pineone.icbms.so.interfaces.database.service.DataBaseStore;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Database Manager implements.<BR/>
 *
 * Created by uni4love on 2017. 1. 8..
 */
@Component
public class DatabaseManager implements IDatabaseManager {

    @Autowired
    //IDataBaseStore dataBaseStore;
    DataBaseStore dataBaseStore;

    /**
     * singleton holder
     */
    private static final class SingletonHolder {
        static final DatabaseManager singleton = new DatabaseManager();
    }

    /**
     * constructor
     */
    public DatabaseManager() {
    }

    /**
     * return singleton instance.<BR/>
     *
     * @return singleton instance
     */
    public static DatabaseManager getInstance() {
//        return SingletonHolder.singleton;
        return new DatabaseManager();
    }

    @Override
    public List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri) {
        return dataBaseStore.getProfileListByContextModelSidAndLocationUri(contextModelSid, locationUri);
        //return retrieveProfileListByContextModelSidAndLocationUri(contextModelSid, locationUri);
    }

    @Override
    public OrchestrationServiceForDB getOrchestrationServiceById(String id) {
        return dataBaseStore.getOrchestrationServiceById(id);
    }

    @Override
    public AspectForDB getAspect(String virtualObjectId) {
        return dataBaseStore.getAspect(virtualObjectId);
    }

    @Override
    public VirtualObjectForDB getVirtualObjectById(String id) {
        return dataBaseStore.getVirtualObjectById(id);
    }

    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return dataBaseStore.getVirtualObjectListByOrchestrationId(orchestrationServiceId);
    }

    @Override
    public List<DeviceForDB> getDeviceList(String functionUri, String aspect, String locationUri) {
        //implements.
        return dataBaseStore.getDeviceList(functionUri, aspect, locationUri);
    }

    @Override
    public FixedDeviceForDB getFixedDevice(String id) {
        return dataBaseStore.getFixedDevice(id);
    }

    @Override
    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return dataBaseStore.getCompositeVirtualObjectListByOrchestrationId(orchestrationServiceId);
    }

    @Override
    public DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId) {
        return dataBaseStore.getDeviceControlByDeviceIdAndContextModelID(deviceId, contextModelId);
    }

    @Override
    public DeviceControlForDB getDeviceControlValues(String deviceId, String contextModelId) {
        return dataBaseStore.getDeviceControlByDeviceIdAndContextModelID(deviceId, contextModelId);
    }

    @Override
    public ProfileForDB getProfileById(String profileId){
        return dataBaseStore.getProfile(profileId);
    }

    @Override
    public void createTracking(TrackingEntity trackingEntity) {
        dataBaseStore.createTracking(trackingEntity);
    }
}
