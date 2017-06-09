package com.pineone.icbms.so.interfaces.database.logic.mapper;

import com.pineone.icbms.so.interfaces.database.logic.itf.mapper.ICM_CI_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.mapper.CM_CI_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.mapper.CM_CI_MapperRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 11..
 */

@Service
public class CM_CI_Mapper_DAO implements ICM_CI_Mapper_DAO {

    @Autowired
    CM_CI_MapperRepository cm_ci_mapperRepository;

    @Override
    public CM_CI_MapperForDB retrieveCM_CI_MapperByMapperId(String mapperId){
        //
        CM_CI_MapperForDB cm_ci_mapperForDB = cm_ci_mapperRepository.findOne(mapperId);
        return cm_ci_mapperForDB;
    }

    @Override
    public List<CM_CI_MapperForDB> retrieveCM_CI_MapperListBYCMId(String cmId) {
        //
        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapperRepository.findAllByContextModelId(cmId);
        return cm_ci_mapperForDBList;
    }

    @Override
    public List<CM_CI_MapperForDB> retrieveCM_CI_Mapper() {
        //
        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapperRepository.findAll();
        return cm_ci_mapperForDBList;
    }

    @Override
    public CM_CI_MapperForDB createCM_CI_Mapper(String cmId, String ciId) {
        //
        CM_CI_MapperForDB cm_ci_mapperForDB = new CM_CI_MapperForDB(cmId, ciId);
        String cm_ci_mapperID = "CCM-" + IdUtils.createRandomUUID();
        cm_ci_mapperForDB.setId(cm_ci_mapperID);
        cm_ci_mapperRepository.save(cm_ci_mapperForDB);
        return cm_ci_mapperForDB;
    }

    @Override
    public List<CM_CI_MapperForDB> updateCM_CI_Mapper(String cmId, List<String> ciIdList) {
        //
        List<CM_CI_MapperForDB> cm_ci_mapperForDBList = cm_ci_mapperRepository.findAllByContextModelId(cmId);

        //기존 값 삭제
        for(CM_CI_MapperForDB cm_ci_mapperForDB : cm_ci_mapperForDBList){
            deleteCM_CI_Mapper(cm_ci_mapperForDB.getId());
        }

        //등록된 값 추가
        for(String ciId : ciIdList) {
            CM_CI_MapperForDB cm_ci_mapperForDB = new CM_CI_MapperForDB(cmId, ciId);
            String cm_ci_mapperId = "CCM-" + IdUtils.createRandomUUID();
            cm_ci_mapperForDB.setId(cm_ci_mapperId);

            cm_ci_mapperRepository.save(cm_ci_mapperForDB);
        }

        cm_ci_mapperForDBList = cm_ci_mapperRepository.findAllByContextModelId(cmId);
        return cm_ci_mapperForDBList;
    }

    @Override
    public String deleteCM_CI_Mapper(String id) {
        //
        CM_CI_MapperForDB cm_ci_mapperForDB = cm_ci_mapperRepository.findOne(id);
        cm_ci_mapperRepository.delete(id);
        return "DELETE : " + cm_ci_mapperForDB;
    }
}
