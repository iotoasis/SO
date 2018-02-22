package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.model.*;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 10..
 */
public interface IDataBaseStore {
    //
	ContextModelForDB getContextModelById(String contextModelId);
	
    //List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId);

    List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId);
	List<RuleBodyForDB> getRuleBodyListByOsId(String osId);
	CompositeVirtualObjectForDB getCvoById(String id);

    List<VirtualObjectForDB> getVirtualObjectListByCompositeVirtualObjectId(String compositeVirtualObjectId);
    List<RuleItemForDB> getRuleVirtualObjectListByCvoId(String compositeVirtualObjectId);

    DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId);

    List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri);

    OrchestrationServiceForDB getOrchestrationServiceById(String id);

    AspectForDB getAspect(String virtualObjectId);

    VirtualObjectForDB getVirtualObjectById(String id);

    List<DeviceForDB> getDeviceList(String functionUri, String aspect, String locationUri);

    FixedDeviceForDB getFixedDevice(String id);

    ProfileForDB getProfile(String id);
	List<String> getDepProfileById(String profileId);
//    FixedDeviceForDB getFixedDevice(String id);
	List<ProfileForDB> getAllProfile();

    void createTracking(TrackingEntity trackingEntity);
    
    // grib session
    void createSessionData(SessionEntity sessionEntity);
    void createSessionDataLocation(SessionEntity sessionEntity);
    void createSessionDataDevice(SessionEntity sessionEntity);
    void createSessionDataVo(SessionEntity sessionEntity);
    void updateSessionData(SessionEntity sessionEntity);
    SessionEntity getSessionData(String sessionId);

	LocationForDB getLocationById(String id);
	FunctionalityForDB getFunction(String id);

	List<DeviceTypeForDB> retrieveDeviceType();
	DeviceTypeForDB retrieveDeviceTypeById(String id);

	List<MeasuringVoForDB> getMeasuringVoList(String osId);
}
