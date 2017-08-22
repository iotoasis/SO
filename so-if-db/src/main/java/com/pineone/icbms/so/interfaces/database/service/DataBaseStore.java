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
    ContextInformationDao contextInformationDao;

    @Autowired
    DeviceControlDao deviceControlDao;

    @Autowired
    TrackingDao trackingDao;

    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId){
        log.warn("getVirtualObjectListByOrchestrationId : orchestrationServiceId {}", orchestrationServiceId);
//        //
//        List<OS_VO_MapperForDB> os_vo_mapperForDBList = os_vo_mapper_dao.retrieveOS_VO_MapperListByServiceId(orchestrationServiceId);
//        List<VirtualObjectForDB> virtualObjectForDBList = new ArrayList<>();
//
//        for(OS_VO_MapperForDB os_vo_mapperForDB : os_vo_mapperForDBList){
//            virtualObjectForDBList.add(virtualObjectDao.retrieveVirtualObject(os_vo_mapperForDB.getVirtualObjectId()));
//        }
//        return virtualObjectForDBList;

        List<VirtualObjectForDB> os_vo_mapperForDBList = virtualObjectDao.getVirtualObjectListByOrchestrationId(orchestrationServiceId);
        List<VirtualObjectForDB> virtualObjectForDBList = virtualObjectDao.retrieveVirtualObject(os_vo_mapperForDBList);

        return virtualObjectForDBList;
    }

    @Override
    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId){
        log.warn("getCompositeVirtualObjectListByOrchestrationId : orchestrationServiceId {}", orchestrationServiceId);
//        //
//        List<OS_CVO_MapperForDB> os_cvo_mapperForDBList = os_cvo_mapper_dao.retrieveOS_CVO_MapperListByServiceId(orchestrationServiceId);
//        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = new ArrayList<>();
//
//        for(OS_CVO_MapperForDB os_cvo_mapperForDB : os_cvo_mapperForDBList){
//            //
//            compositeVirtualObjectForDBList.add(compositeVirtualObjectDao.retrieveCompositeVirtualObject(
//                    os_cvo_mapperForDB.getCompositeVirtualObjectId()));
//        }
//
//        for(CompositeVirtualObjectForDB compositeVirtualObjectForDB : compositeVirtualObjectForDBList){
//            compositeVirtualObjectForDB.setVirtualObjectForDBList(getVirtualObjectListByCompositeVirtualObjectId(
//                    compositeVirtualObjectForDB.getId()));
//        }
//        return compositeVirtualObjectForDBList;


        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = compositeVirtualObjectDao.retrieveCompositeVirtualObjectListByOrchestrationId(orchestrationServiceId);

        for(CompositeVirtualObjectForDB compositeVirtualObjectForDB : compositeVirtualObjectForDBList){
            compositeVirtualObjectForDB.setVirtualObjectForDBList(
                    getVirtualObjectListByCompositeVirtualObjectId(compositeVirtualObjectForDB.getId())
            );
        }

        return compositeVirtualObjectForDBList;
    }

    /*
     * TODO cvo 에서 vo 목록을 가져온다
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
    public List<ContextInformationForDB> getContextInformationListByContextModelId(String contextModelId){
        log.warn("getContextInformationListByContextModelId : contextModelId {}", contextModelId);
//        //
//        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapper_dao.retrieveCM_CI_MapperListBYCMId(contextModelId);
//        List<ContextInformationForDB> contextInformationForDBList = new ArrayList<>();
//
//        for(CM_CI_MapperForDB cm_ci_mapperForDB : cm_ci_mapperForDBList){
//            contextInformationForDBList.add(contextInformationDao.retrieveContextInformation(cm_ci_mapperForDB.getContextInformationId()));
//        }
//        return contextInformationForDBList;
        return null;
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
        log.warn("getProfileListByContextModelSidAndLocationUri : contextModelSid: {}, locationUri:{}", contextModelSid, locationUri);
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

        // get cvo
        //List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = compositeVirtualObjectDao.getCompositeVirtualObjectListByOrchestrationId(id);
        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = this.getCompositeVirtualObjectListByOrchestrationId(id);

        // get vo
        List<VirtualObjectForDB> virtualObjectForDBList = virtualObjectDao.getVirtualObjectListByOrchestrationId(id);

        // set cvo list, vo list
        orchestrationServiceForDB.setCompositeVirtualObjectForDBList(compositeVirtualObjectForDBList);
        orchestrationServiceForDB.setVirtualObjectForDBList(virtualObjectForDBList);

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
        log.warn("createTracking : TrackingEntity: {}", trackingEntity);
        trackingDao.createTracking(trackingEntity);
    }
}
