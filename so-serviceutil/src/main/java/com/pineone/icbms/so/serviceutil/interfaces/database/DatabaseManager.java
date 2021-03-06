package com.pineone.icbms.so.serviceutil.interfaces.database;

import com.pineone.icbms.so.interfaces.database.model.*;
import com.pineone.icbms.so.interfaces.database.service.DataBaseStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.PostConstruct;

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

    static DataBaseStore dataBaseStoreClone;
    static DatabaseManager databaseManager;
    /**
     * constructor
     */
    
    @PostConstruct
    public void InitDatabaseManager() {
    	dataBaseStoreClone = dataBaseStore;
    	databaseManager = this;
    }

    /**
     * return singleton instance.<BR/>
     *
     * @return singleton instance
     */
    public static DatabaseManager getInstance() {
//        return SingletonHolder.singleton;
        //return new DatabaseManager();
    	return databaseManager;
    }

	@Override
	public ContextModelForDB getContextModelById(String contextModelId) {
		return dataBaseStore.getContextModelById(contextModelId);
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
/*
    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId) {
        return dataBaseStore.getVirtualObjectListByOrchestrationId(orchestrationServiceId);
    }
*/
    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByCompositeVirtualObjectId(String id) {
        return dataBaseStore.getVirtualObjectListByCompositeVirtualObjectId(id);
    }

    @Override
    public List<RuleItemForDB> getRuleItemListByRuleBodyId(String id) {
        return dataBaseStore.getRuleVirtualObjectListByCvoId(id);
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

    //rule_body
	@Override
	public List<RuleBodyForDB> getRuleBodyListByOsId(String osId) {
		return dataBaseStore.getRuleBodyListByOsId(osId);
	}

	@Override
	public CompositeVirtualObjectForDB getCompositeVirtualObjectById(String id) {
		return dataBaseStore.getCvoById(id);
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
    public List<String> getDepProfileById(String profileId){
        return dataBaseStore.getDepProfileById(profileId);
    }

    @Override
    public List<ProfileForDB> getAllProfile(){
        return dataBaseStore.getAllProfile();
    }
    
    @Override
    public void createTracking(TrackingEntity trackingEntity) {
        dataBaseStore.createTracking(trackingEntity);
    }
    
    // grib session
    @Override
    public void createSessionData(SessionEntity sessionEntity) {
        dataBaseStore.createSessionData(sessionEntity);
    }
    @Override
    public void createSessionDataLocation(SessionEntity sessionEntity) {
        dataBaseStore.createSessionDataLocation(sessionEntity);
    }
    @Override
    public void createSessionDataDevice(SessionEntity sessionEntity) {
        dataBaseStore.createSessionDataDevice(sessionEntity);
    }
    @Override
    public void createSessionDataVo(SessionEntity sessionEntity) {
        dataBaseStore.createSessionDataVo(sessionEntity);
    }
    @Override
    public void updateSessionData(SessionEntity sessionEntity) {
        dataBaseStore.updateSessionData(sessionEntity);
    }

    @Override
    public SessionEntity getSessionData(String sessionId) {
        return dataBaseStore.getSessionData(sessionId);
    }

    @Override
	public LocationForDB getLocationById(String id) {
        return dataBaseStore.getLocationById(id);
	}

    @Override
	public FunctionalityForDB getFunction(String id) {
    	return dataBaseStore.getFunction(id);
	}

	@Override
	public List<DeviceTypeForDB> retrieveDeviceType() {
		return dataBaseStore.retrieveDeviceType();
	}

	@Override
	public DeviceTypeForDB retrieveDeviceTypeById(String id) {
		return dataBaseStore.retrieveDeviceTypeById(id);
	}

	@Override
	public List<NonDeviceCvoForDB> retrieveNonDeviceCvoList(String nCvoId, String osId) {
		return dataBaseStore.retrieveNonDeviceCvoList(nCvoId, osId);
	}
   
}
