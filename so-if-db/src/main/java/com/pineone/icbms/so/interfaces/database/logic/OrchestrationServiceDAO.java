package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.OrchestrationServiceData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IDataBaseStore;
import com.pineone.icbms.so.interfaces.database.logic.itf.IOrchestrationServiceDAO;
import com.pineone.icbms.so.interfaces.database.logic.itf.mapper.IOS_CVO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.logic.itf.mapper.IOS_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.OS_CVO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.OS_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.OS_CVO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.OS_VO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.OrchestrationServiceRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 30..
 */
@Service
//  OrchestrationService Data Access 기능 구현
public class OrchestrationServiceDAO implements IOrchestrationServiceDAO {

    @Autowired
    OrchestrationServiceRepository orchestrationServiceRepository;

    @Autowired
    IOS_VO_Mapper_DAO os_vo_mapper_dao;

    @Autowired
    IOS_CVO_Mapper_DAO os_cvo_mapper_dao;

    @Autowired
    IDataBaseStore dataBaseStore;

    // OrchestrationService 단일 조회
    @Override
    public OrchestrationServiceForDB retrieveOrchestrationService(String id) {
        //
        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceRepository.findOne(id);
        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = dataBaseStore.getCompositeVirtualObjectListByOrchestrationId(id);
        List<VirtualObjectForDB> virtualObjectForDBList = dataBaseStore.getVirtualObjectListByOrchestrationId(id);
        orchestrationServiceForDB.setCompositeVirtualObjectForDBList(compositeVirtualObjectForDBList);
        orchestrationServiceForDB.setVirtualObjectForDBList(virtualObjectForDBList);
        return orchestrationServiceForDB;
    }

    // OrchestrationService 전체 조회
    @Override
    public List<OrchestrationServiceForDB> retrieveOrchestrationServiceList() {
        //
        List<OrchestrationServiceForDB> orchestrationServiceForDBList = orchestrationServiceRepository.findAll();

        for(OrchestrationServiceForDB orchestrationServiceForDB : orchestrationServiceForDBList){
            orchestrationServiceForDB.setVirtualObjectForDBList(dataBaseStore.getVirtualObjectListByOrchestrationId(
                    orchestrationServiceForDB.getId()));
            orchestrationServiceForDB.setCompositeVirtualObjectForDBList(dataBaseStore.getCompositeVirtualObjectListByOrchestrationId(
                    orchestrationServiceForDB.getId()
            ));
        }
        return orchestrationServiceForDBList;
    }

    // OrchestrationService 생성 기능
    @Override
    public OrchestrationServiceForDB createOrchestrationService(OrchestrationServiceData orchestrationServiceData) {
        //
        OrchestrationServiceForDB orchestrationServiceForDB =
                createOrchestrationServiceDataConversion(orchestrationServiceData);

        String osId = "OS-" + IdUtils.createRandomUUID();
        orchestrationServiceForDB.setId(osId);

        List<String> cvoIdList = orchestrationServiceData.getCompositeVirtualObjectIdList();
        if(cvoIdList != null){
            for(String cvoId : cvoIdList){
                os_cvo_mapper_dao.createOS_CVO_Mapper(osId, cvoId);
            }
        }

        List<String> voIdList = orchestrationServiceData.getVirtualObjectIdList();
        if(voIdList != null) {
            for (String voId : voIdList) {
                os_vo_mapper_dao.createOS_VO_Mapper(osId, voId);
            }
        }

        System.out.println(orchestrationServiceForDB.toString());


        orchestrationServiceForDB.setVirtualObjectForDBList(dataBaseStore.getVirtualObjectListByOrchestrationId(
                orchestrationServiceForDB.getId()));
        orchestrationServiceForDB.setCompositeVirtualObjectForDBList(dataBaseStore.getCompositeVirtualObjectListByOrchestrationId(
                orchestrationServiceForDB.getId()
        ));

        orchestrationServiceRepository.save(orchestrationServiceForDB);
        return orchestrationServiceForDB;
    }

    // OrchestrationService 갱신 기능
    @Override
    public OrchestrationServiceForDB updateOrchestrationService(String id, OrchestrationServiceData orchestrationServiceData) {
        //
        OrchestrationServiceForDB orchestrationServiceForDB;
        if(orchestrationServiceRepository.findOne(id) != null){
            orchestrationServiceForDB = updateOrchestrationServiceDataConversion(orchestrationServiceData);
            orchestrationServiceForDB.setId(id);
            orchestrationServiceForDB.setCreated_date(orchestrationServiceRepository.findOne(id).getCreated_date());

            List<String> cvoIdList = orchestrationServiceData.getCompositeVirtualObjectIdList();
            if(cvoIdList != null){
                os_cvo_mapper_dao.updateOS_CVO_Mapper(id, cvoIdList);
            }

            List<String> voIdList = orchestrationServiceData.getVirtualObjectIdList();
            if(voIdList != null) {
                os_vo_mapper_dao.updateOS_VO_Mapper(id, voIdList);
            }
            orchestrationServiceRepository.delete(id);

            orchestrationServiceForDB.setVirtualObjectForDBList(dataBaseStore.getVirtualObjectListByOrchestrationId(
                    orchestrationServiceForDB.getId()));
            orchestrationServiceForDB.setCompositeVirtualObjectForDBList(dataBaseStore.getCompositeVirtualObjectListByOrchestrationId(
                    orchestrationServiceForDB.getId()
            ));

            orchestrationServiceRepository.save(orchestrationServiceForDB);
        } else {
            orchestrationServiceForDB = createOrchestrationService(orchestrationServiceData);
        }

        return orchestrationServiceForDB;
    }

    //  OrchestrationService 삭제 기능
    @Override
    public String deleteOrchestrationService(String id) {
        //
        OrchestrationServiceForDB orchestrationServiceForDB = orchestrationServiceRepository.findOne(id);
        List<OS_VO_MapperForDB> os_vo_mapperForDBList = os_vo_mapper_dao.retrieveOS_VO_MapperListByServiceId(id);
        List<OS_CVO_MapperForDB> os_cvo_mapperForDBList = os_cvo_mapper_dao.retrieveOS_CVO_MapperListByServiceId(id);

        if(os_cvo_mapperForDBList != null){
            for(OS_CVO_MapperForDB os_cvo_mapperForDB : os_cvo_mapperForDBList){
                os_cvo_mapper_dao.deleteOS_CVO_Mapper(os_cvo_mapperForDB.getId());
            }

        }
        if(os_vo_mapperForDBList != null) {
            for (OS_VO_MapperForDB os_vo_mapperForDB : os_vo_mapperForDBList) {
                os_vo_mapper_dao.deleteOS_VO_Mapper(os_vo_mapperForDB.getId());
            }
        }
        orchestrationServiceRepository.delete(id);
        String message = "Delete " + orchestrationServiceForDB.toString();
        return message;
    }


    private OrchestrationServiceForDB createOrchestrationServiceDataConversion(OrchestrationServiceData orchestrationServiceData) {
        //
        OrchestrationServiceForDB orchestrationServiceForDB = new OrchestrationServiceForDB(null,
                orchestrationServiceData.getName(), orchestrationServiceData.getDescription(),
                orchestrationServiceData.getParent_id());
        return orchestrationServiceForDB;
    }

    private OrchestrationServiceForDB updateOrchestrationServiceDataConversion(OrchestrationServiceData orchestrationServiceData) {
        //
        OrchestrationServiceForDB orchestrationServiceForDB = new OrchestrationServiceForDB(null,
                orchestrationServiceData.getName(), orchestrationServiceData.getDescription(),
                orchestrationServiceData.getParent_id(), Calendar.getInstance().getTime());
        return orchestrationServiceForDB;
    }
}
