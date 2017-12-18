package com.pineone.icbms.so.serviceutil.interfaces.database;

import com.pineone.icbms.so.interfaces.database.model.*;

import java.util.List;

/**
 * Database interface<BR/>
 *
 * Created by uni4love on 2017. 1. 8..
 */
public interface IDatabaseManager {

	ContextModelForDB getContextModelById(String contextModelId);
	
    List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri);

    OrchestrationServiceForDB getOrchestrationServiceById(String id);

    AspectForDB getAspect(String virtualObjectId);

    VirtualObjectForDB getVirtualObjectById(String id);

    //List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId);
	List<VirtualObjectForDB> getVirtualObjectListByCompositeVirtualObjectId(String id);
	List<RuleItemForDB> getRuleItemListByRuleBodyId(String id);

    List<DeviceForDB> getDeviceList(String functionUri, String aspect, String locationUri);

    FixedDeviceForDB getFixedDevice(String id);

    List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId);
	List<RuleBodyForDB> getRuleBodyListByOsId(String osId);
    
    CompositeVirtualObjectForDB getCompositeVirtualObjectById(String id);

    
    DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId);

    DeviceControlForDB getDeviceControlValues(String deviceId, String contextModelId);

    ProfileForDB getProfileById(String profileId);
	List<String> getDepProfileById(String profileId);
    public List<ProfileForDB> getAllProfile();

    void createTracking(TrackingEntity trackingEntity);
    
    LocationForDB getLocationById(String id);
    
    // grib session
    void createSessionData(SessionEntity sessionEntity);
    void createSessionDataLocation(SessionEntity sessionEntity);
    void createSessionDataDevice(SessionEntity sessionEntity);
    void createSessionDataVo(SessionEntity sessionEntity);
    void updateSessionData(SessionEntity sessionEntity);
    SessionEntity getSessionData(String sessionId);

	FunctionalityForDB getFunction(String id);

	List<DeviceTypeForDB> retrieveDeviceType();
	DeviceTypeForDB retrieveDeviceTypeById(String id);

}
