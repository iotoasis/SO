package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ContextModelData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IContextModelDAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.CM_CI_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.CM_CI_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.ContextModelRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * CM Data Access 기능 구현<BR/>
 *
 * Created by melvin on 2017. 3. 29..
 */
@Service
public class ContextModelDAO implements IContextModelDAO {
    //
    @Autowired
    ContextModelRepository contextModelRepository;

    @Autowired
    CM_CI_Mapper_DAO cm_ci_mapper_dao;

    @Autowired
    DataBaseStoreDAO dataBaseStoreDAO;

    // CM 단일 조회 기능
    @Override
    public ContextModelForDB retrieveContextModel(String id) {
        //
        ContextModelForDB contextModelForDB = contextModelRepository.findOne(id);
        List<ContextInformationForDB> contextInformationForDBList = dataBaseStoreDAO.getContextInformationListByContextModelId(id);
        contextModelForDB.setContextInformationForDBList(contextInformationForDBList);
        return contextModelRepository.findOne(id);
    }

    // CM 리스트 조회 기능
    @Override
    public List<ContextModelForDB> retrieveContextModelList() {
        //
        List<ContextModelForDB> contextModelForDBList = contextModelRepository.findAll();
        for(ContextModelForDB contextModelForDB : contextModelForDBList){
            contextModelForDB.setContextInformationForDBList(dataBaseStoreDAO.getContextInformationListByContextModelId(
                    contextModelForDB.getId()
            ));
        }
        return contextModelForDBList;
    }

    // CM 생성 기능
    @Override
    public ContextModelForDB createContextModel(ContextModelData contextModelData) {
        //
        ContextModelForDB contextModelForDB = createContextModelDataConversion(contextModelData);

        String cmId = "CM-" + IdUtils.createRandomUUID();
        contextModelForDB.setId(cmId);

        List<String> ciIdList = contextModelData.getContextInformationIdList();

        for(String ciId : ciIdList){
            cm_ci_mapper_dao.createCM_CI_Mapper(cmId, ciId);
        }
        contextModelForDB.setContextInformationForDBList(dataBaseStoreDAO.getContextInformationListByContextModelId(
                contextModelForDB.getId()));
        contextModelRepository.save(contextModelForDB);
        //     virtualObjectForDB.setId((int)virtualObjectRepository.count()+1);

//        System.out.println("******************save" + compositeVirtualObjectForDB.toString());
//        return "Create  " + compositeVirtualObjectForDB.toString();
        return contextModelForDB;
    }

    //  CM 갱신 기능
    @Override
    public ContextModelForDB updateContextModel(String id, ContextModelData contextModelData) {
        //
        ContextModelForDB contextModelForDB;
        if(contextModelRepository.findOne(id) != null){
            contextModelForDB = updateContextModelDataConversion(contextModelData);
            contextModelForDB.setId(id);
            contextModelForDB.setCreated_date(contextModelRepository.findOne(id).getCreated_date());
            List<String> ciIdList = contextModelData.getContextInformationIdList();

            cm_ci_mapper_dao.updateCM_CI_Mapper(id, ciIdList);
            contextModelRepository.delete(id);
            contextModelForDB.setContextInformationForDBList(dataBaseStoreDAO.getContextInformationListByContextModelId(
                    contextModelForDB.getId()));
            contextModelRepository.save(contextModelForDB);
        } else {
            contextModelForDB = createContextModel(contextModelData);
        }
        return contextModelForDB;
    }


    // CM 삭제 기능
    @Override
    public String deleteContextModel(String id) {
        //
        ContextModelForDB contextModelForDB = contextModelRepository.findOne(id);
        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapper_dao.retrieveCM_CI_MapperListBYCMId(id);

        for(CM_CI_MapperForDB cm_ci_mapperForDB : cm_ci_mapperForDBList){
            cm_ci_mapper_dao.deleteCM_CI_Mapper(cm_ci_mapperForDB.getId());
        }
        contextModelRepository.delete(id);
        String resultMessage = "Delete  " + contextModelForDB.toString();
        return resultMessage;

    }

    // SDA 와 연동하는 데이터 정보를 통해 CM 조회
//    @Override
//    public ContextModelForDB retrieveContextModelByContextModelAPI(String contextModelId) {
//        return contextModelRepository.findByContextModelId(contextModelId);
//    }

    private ContextModelForDB createContextModelDataConversion(ContextModelData contextModelData) {
        //
        ContextModelForDB contextModelForDB = new ContextModelForDB(null, contextModelData.getName(),
                 contextModelData.getDescription());
        return contextModelForDB;
    }

    private ContextModelForDB updateContextModelDataConversion(ContextModelData contextModelData) {
        //
        ContextModelForDB contextModelForDB = new ContextModelForDB(null, contextModelData.getName(),
                contextModelData.getDescription(), Calendar.getInstance().getTime());
        return contextModelForDB;
    }
}
