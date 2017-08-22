package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.model.*;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 10..
 */
public interface IDataBaseStore {
    //
    List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId);

    List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId);

    List<VirtualObjectForDB> getVirtualObjectListByCompositeVirtualObjectId(String compositeVirtualObjectId);

    List<ContextInformationForDB> getContextInformationListByContextModelId(String contextModelId);

    DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId);

    List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri);

    OrchestrationServiceForDB getOrchestrationServiceById(String id);

    AspectForDB getAspect(String virtualObjectId);

    VirtualObjectForDB getVirtualObjectById(String id);

    List<DeviceForDB> getDeviceList(String functionUri, String aspect, String locationUri);

    FixedDeviceForDB getFixedDevice(String id);

    ProfileForDB getProfile(String id);
//    FixedDeviceForDB getFixedDevice(String id);

    void createTracking(TrackingEntity trackingEntity);
}
