package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.logic.itf.*;
import com.pineone.icbms.so.interfaces.database.logic.mapper.CM_CI_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.CVO_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.OS_CVO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.OS_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.*;
import com.pineone.icbms.so.interfaces.database.model.mapper.CM_CI_MapperForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.CVO_VO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.OS_CVO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.OS_VO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.mapper.OS_VO_MapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2017. 5. 10..
 */
@Service
public class DataBaseStoreDAO implements IDataBaseStore{

    @Autowired
    IProfileDAO profileDAO;

    @Autowired
    IAspectDAO aspectDAO;

    @Autowired
    FixedDeviceDAO fixedDeviceDAO;

    @Autowired
    IDeviceDAO deviceDAO;

    @Autowired
    IVirtualObjectDAO virtualObjectDAO;

    @Autowired
    ICompositeVirtualObjectDAO compositeVirtualObjectDAO;

    @Autowired
    IOrchestrationServiceDAO orchestrationServiceDAO;

    @Autowired
    OS_VO_Mapper_DAO os_vo_mapper_dao;

    @Autowired
    CVO_VO_Mapper_DAO cvo_vo_mapper_dao;

    @Autowired
    CM_CI_Mapper_DAO cm_ci_mapper_dao;

    @Autowired
    OS_CVO_Mapper_DAO os_cvo_mapper_dao;

    @Autowired
    ContextInformationDAO contextInformationDAO;

    @Autowired
    DeviceControlDAO deviceControlDAO;

    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByOrchestrationId(String orchestrationServiceId){
        //
        List<OS_VO_MapperForDB> os_vo_mapperForDBList = os_vo_mapper_dao.retrieveOS_VO_MapperListByServiceId(orchestrationServiceId);
        List<VirtualObjectForDB> virtualObjectForDBList = new ArrayList<>();

        for(OS_VO_MapperForDB os_vo_mapperForDB : os_vo_mapperForDBList){
            virtualObjectForDBList.add(virtualObjectDAO.retrieveVirtualObject(os_vo_mapperForDB.getVirtualObjectId()));
        }
        return virtualObjectForDBList;
    }

    @Override
    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectListByOrchestrationId(String orchestrationServiceId){
        //
        List<OS_CVO_MapperForDB> os_cvo_mapperForDBList = os_cvo_mapper_dao.retrieveOS_CVO_MapperListByServiceId(orchestrationServiceId);
        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = new ArrayList<>();

        for(OS_CVO_MapperForDB os_cvo_mapperForDB : os_cvo_mapperForDBList){
            //
            compositeVirtualObjectForDBList.add(compositeVirtualObjectDAO.retrieveCompositeVirtualObject(
                    os_cvo_mapperForDB.getCompositeVirtualObjectId()));
        }

        for(CompositeVirtualObjectForDB compositeVirtualObjectForDB : compositeVirtualObjectForDBList){
            compositeVirtualObjectForDB.setVirtualObjectForDBList(getVirtualObjectListByCompositeVirtualObjectId(
                    compositeVirtualObjectForDB.getId()));
        }
        return compositeVirtualObjectForDBList;
    }

    @Override
    public List<VirtualObjectForDB> getVirtualObjectListByCompositeVirtualObjectId(String compositeVirtualObjectId){
        //
        List<CVO_VO_MapperForDB> cvo_vo_mapperForDBList = cvo_vo_mapper_dao.retrieveCVO_VO_MapperListByCVOID(compositeVirtualObjectId);
        List<VirtualObjectForDB> virtualObjectForDBList = new ArrayList<>();

        for(CVO_VO_MapperForDB cvo_vo_mapperForDB : cvo_vo_mapperForDBList){
            virtualObjectForDBList.add(virtualObjectDAO.retrieveVirtualObject(cvo_vo_mapperForDB.getVirtualObjectId()));
        }
        return virtualObjectForDBList;
    }

    @Override
    public List<ContextInformationForDB> getContextInformationListByContextModelId(String contextModelId){
        //
        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapper_dao.retrieveCM_CI_MapperListBYCMId(contextModelId);
        List<ContextInformationForDB> contextInformationForDBList = new ArrayList<>();

        for(CM_CI_MapperForDB cm_ci_mapperForDB : cm_ci_mapperForDBList){
            contextInformationForDBList.add(contextInformationDAO.retrieveContextInformation(cm_ci_mapperForDB.getContextInformationId()));
        }
        return contextInformationForDBList;
    }

    @Override
    public DeviceControlForDB getDeviceControlByDeviceIdAndContextModelID(String deviceId, String contextModelId){
        //

        DeviceControlForDB deviceControlForDB = deviceControlDAO.retrieveDeviceControlByDeviceIdAndContextModelId(deviceId, contextModelId);
        if(deviceControlForDB == null){
            return null;
        }
        return  deviceControlForDB;
    }

    @Override
    public List<ProfileForDB> getProfileListByContextModelSidAndLocationUri(String contextModelSid, String locationUri) {
        return profileDAO.getProfileByContextModelAndLocation(contextModelSid, locationUri);
    }

    @Override
    public OrchestrationServiceForDB getOrchestrationServiceById(String id) {
        return orchestrationServiceDAO.retrieveOrchestrationService(id);
    }

    @Override
    public AspectForDB getAspect(String virtualObjectId) {
        return aspectDAO.retrieveAspectByVirtualObjectId(virtualObjectId);
    }

    @Override
    public VirtualObjectForDB getVirtualObjectById(String id) {
        return virtualObjectDAO.retrieveVirtualObject(id);
    }

    @Override
    public List<DeviceForDB> getDeviceList(String functionalityUri, String aspect, String locationUri) {
        //implements.
        return deviceDAO.retrieveDeviceList(functionalityUri, aspect, locationUri);
    }

    @Override
    public FixedDeviceForDB getFixedDevice(String id) {
        return fixedDeviceDAO.retrieveFixedDevice(id);
    }

    @Override
    public ProfileForDB getProfile(String id){
        return profileDAO.retrieveProfile(id);
    }
}
