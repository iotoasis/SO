package com.pineone.icbms.so.interfaces.database.service;

import com.pineone.icbms.so.interfaces.database.dao.*;
import com.pineone.icbms.so.interfaces.database.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jonghee on 2017-06-15.
 */
@Service
public class DataBaseStore implements IDataBaseStore {

    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProfileDao profileDao;

    @Autowired
    AspectDao aspectDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    FixedDeviceDao fixedDeviceDao;

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    VirtualObjectDao virtualObjectDao;

    @Autowired
    CompositeVirtualObjectDao compositeVirtualObjectDao;

    @Autowired
    OrchestrationServiceDao orchestrationServiceDao;

    @Autowired
    ContextModelDao contextModelDao;

    @Autowired
    DeviceControlDao deviceControlDao;

    @Autowired
    TrackingDao trackingDao;
    
	// grib session
    @Autowired
    SessionDao sessionDao;

	@Override
	public ContextModelForDB getContextModelById(String contextModelId) {
		return contextModelDao.retrieve(contextModelId);
	}
/*    
    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId){
        log.warn("getVirtualObjectListByOrchestrationId : orchestrationServiceId {}", orchestrationServiceId);

        //
        List<VirtualObjectForDB> os_vo_mapperForDBList = virtualObjectDao.getVirtualObjectListByOrchestrationId(orchestrationServiceId);
        List<VirtualObjectForDB> virtualObjectForDBList = virtualObjectDao.retrieveVirtualObject(os_vo_mapperForDBList);

        return virtualObjectForDBList;
    }
*/
    @Override
    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId){
        log.warn("getCompositeVirtualObjectListByOrchestrationId : orchestrationServiceId {}", orchestrationServiceId);

        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = compositeVirtualObjectDao.retrieveCompositeVirtualObjectListByOrchestrationId(orchestrationServiceId);
    
        List<VirtualObjectForDB> virtualObjectForDBList = null;
        
        for(CompositeVirtualObjectForDB compositeVirtualObjectForDB : compositeVirtualObjectForDBList){
            compositeVirtualObjectForDB.setVirtualObjectForDBList(
                    getVirtualObjectListByCompositeVirtualObjectId(compositeVirtualObjectForDB.getId())
            );
        }

        return compositeVirtualObjectForDBList;
    }

    /*
     * cvo 에서 vo 목록을 가져온다
     */
    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByCompositeVirtualObjectId(String compositeVirtualObjectId){
        log.warn("getVirtualObjectListByCompositeVirtualObjectId : compositeVirtualObjectId {}", compositeVirtualObjectId);
        //
//        List<CVO_VO_MapperForDB> cvo_vo_mapperForDBList = cvo_vo_mapper_dao.retrieveCVO_VO_MapperListByCVOID(compositeVirtualObjectId);
        List<VirtualObjectForDB> virtualObjectForDBList = virtualObjectDao.retrieveVirtualObjectListByCompositeVirtualObjectId(compositeVirtualObjectId);

//        for(CVO_VO_MapperForDB cvo_vo_mapperForDB : cvo_vo_mapperForDBList){
//            virtualObjectForDBList.add(virtualObjectDao.retrieveVirtualObject(cvo_vo_mapperForDB.getVirtualObjectId()));
//        }

        return virtualObjectForDBList;
    }

    @Override
    public DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId){
        log.warn("getDeviceControlByDeviceIdAndContextModelID : deviceId: {}, contextModelId:{}", deviceId, contextModelId);

//        DeviceControlForDB deviceControlForDB = deviceControlDao.retrieveDeviceControlByDeviceIdAndContextModelId(deviceId, contextModelId);
//        if(deviceControlForDB == null){
//            return null;
//        }
//        return  deviceControlForDB;

        return deviceControlDao.retrieveDeviceControlByDeviceIdAndContextModelId(deviceId, contextModelId);
    }

    @Override
    public List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri) {
//        log.warn("getProfileListByContextModelSidAndLocationUri : contextModelSid: {}, locationUri:{}", contextModelSid, locationUri);
        return profileDao.getProfileByContextModelAndLocation(contextModelSid, locationUri);
    }

    /*
     * Orchestration Service Id로 os 목록 조회, os 에 속한 cvo, vo 목록 조회
     */
    @Override
    public OrchestrationServiceForDB getOrchestrationServiceById(String id) {
        log.warn("getOrchestrationServiceById : id: {}", id);

        // get os
        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceDao.retrieveOrchestrationService(id);

        if (orchestrationServiceForDB!=null) {
	        // get cvo
	        //List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = compositeVirtualObjectDao.getCompositeVirtualObjectListByOrchestrationId(id);
	        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = this.getCompositeVirtualObjectListByOrchestrationId(id);
	
	        // get vo
	        //List<VirtualObjectForDB> virtualObjectForDBList = virtualObjectDao.getVirtualObjectListByOrchestrationId(id);
	
	        // set cvo list, vo list
	        orchestrationServiceForDB.setCompositeVirtualObjectForDBList(compositeVirtualObjectForDBList);
	        //orchestrationServiceForDB.setVirtualObjectForDBList(virtualObjectForDBList);
        }
        return orchestrationServiceForDB;
    }

    @Override
    public AspectForDB getAspect(String virtualObjectId) {
        log.warn("getAspect : virtualObjectId: {}", virtualObjectId);
        return aspectDao.retrieveAspectByVirtualObjectId(virtualObjectId);
    }

    @Override
    public VirtualObjectForDB getVirtualObjectById(String id) {
        log.warn("getVirtualObjectById : id: {}", id);
        return virtualObjectDao.retrieveVirtualObject(id);
    }

    @Override
    public List<DeviceForDB> getDeviceList(String functionUri, String aspect, String locationUri) {
        log.warn("getDeviceList : functionUri: {}, aspect: {}, locationUri: {}", functionUri, aspect, locationUri);
        //implements.
        return deviceDao.retrieveDeviceList(functionUri, aspect, locationUri);
    }

    @Override
    public FixedDeviceForDB getFixedDevice(String id) {
        log.warn("getFixedDevice : id: {}", id);
        return fixedDeviceDao.retrieve(id);//.retrieveFixedDevice(id);
    }

    @Override
    public ProfileForDB getProfile(String id){
        log.warn("getProfile : id: {}", id);
        return profileDao.retrieveProfile(id);
    }

    @Override
    public void createTracking(TrackingEntity trackingEntity) {
//        log.warn("createTracking : TrackingEntity: {}", trackingEntity);
        trackingDao.createTracking(trackingEntity);
    }
    
    // grib session
    @Override
    public void createSessionData(SessionEntity sessionEntity) {
        sessionDao.createSessionData(sessionEntity);
    }
    @Override
    public void createSessionDataLocation(SessionEntity sessionEntity) {
        sessionDao.createSessionDataLocation(sessionEntity);
    }
    @Override
    public void createSessionDataDevice(SessionEntity sessionEntity) {
        sessionDao.createSessionDataDevice(sessionEntity);
    }
    @Override
    public void createSessionDataVo(SessionEntity sessionEntity) {
        sessionDao.createSessionDataVo(sessionEntity);
    }
    @Override
    public void updateSessionData(SessionEntity sessionEntity) {
        sessionDao.updateSessionData(sessionEntity);
    }

	public LocationForDB getLocationById(String id) {
        log.warn("getLocationById : id: {}", id);
        return locationDao.retrieve(id);
	}

}
