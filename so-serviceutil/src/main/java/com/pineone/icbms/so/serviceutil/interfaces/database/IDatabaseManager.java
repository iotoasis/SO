package com.pineone.icbms.so.serviceutil.interfaces.database;

import com.pineone.icbms.so.interfaces.database.model.*;

import java.util.List;

/**
 * Database interface<BR/>
 *
 * Created by uni4love on 2017. 1. 8..
 */
public interface IDatabaseManager {

    List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri);

    OrchestrationServiceForDB getOrchestrationServiceById(String id);

    AspectForDB getAspect(String virtualObjectId);

    VirtualObjectForDB getVirtualObjectById(String id);

    List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId);

    List<DeviceForDB> getDeviceList(String functionUri, String aspect, String locationUri);

    FixedDeviceForDB getFixedDevice(String id);

    List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId);

    DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId);

    DeviceControlForDB getDeviceControlValues(String deviceId, String contextModelId);

    ProfileForDB getProfileById(String profileId);

    void createTracking(TrackingEntity trackingEntity);
}
