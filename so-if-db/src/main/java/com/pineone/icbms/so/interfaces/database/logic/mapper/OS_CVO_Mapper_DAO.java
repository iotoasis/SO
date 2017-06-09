package com.pineone.icbms.so.interfaces.database.logic.mapper;

import com.pineone.icbms.so.interfaces.database.logic.itf.mapper.IOS_CVO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.mapper.OS_CVO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.mapper.OS_CVO_MapperRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 15..
 */

@Service
public class OS_CVO_Mapper_DAO implements IOS_CVO_Mapper_DAO {

    @Autowired
    OS_CVO_MapperRepository os_cvo_mapperRepository;

    @Override
    public OS_CVO_MapperForDB retrieveOS_CVO_Mapper(String id) {
        return os_cvo_mapperRepository.findOne(id);
    }

    @Override
    public List<OS_CVO_MapperForDB> retrieveOS_CVO_MapperListByServiceId(String serviceId) {
        return os_cvo_mapperRepository.findAllByOrchestrationServiceId(serviceId);
    }

    @Override
    public List<OS_CVO_MapperForDB> retrieveOS_CVO_Mapper() {
        return os_cvo_mapperRepository.findAll();
    }

    @Override
    public OS_CVO_MapperForDB createOS_CVO_Mapper(String os_Id, String cvo_Id) {
        //
        OS_CVO_MapperForDB os_cvo_mapperForDB = new OS_CVO_MapperForDB(os_Id, cvo_Id);

        String os_cvo_mapperId = "OCM-" + IdUtils.createRandomUUID();
        os_cvo_mapperForDB.setId(os_cvo_mapperId);
        os_cvo_mapperRepository.save(os_cvo_mapperForDB);
        return os_cvo_mapperForDB;
    }

    @Override
    public List<OS_CVO_MapperForDB> updateOS_CVO_Mapper(String os_Id, List<String> cvoIdList) {
        //
        List<OS_CVO_MapperForDB> os_cvo_mapperForDBList = os_cvo_mapperRepository.findAllByOrchestrationServiceId(os_Id);
        for (OS_CVO_MapperForDB os_cvo_mapperForDB : os_cvo_mapperForDBList) {
            deleteOS_CVO_Mapper(os_cvo_mapperForDB.getId());
        }
        for (String cvoId : cvoIdList) {
            OS_CVO_MapperForDB os_cvo_mapperForDB = new OS_CVO_MapperForDB(os_Id, cvoId);
            String os_cvo_mapperID = "OCM-" + IdUtils.createRandomUUID();
            os_cvo_mapperForDB.setId(os_cvo_mapperID);

            os_cvo_mapperRepository.save(os_cvo_mapperForDB);
        }
        os_cvo_mapperForDBList = os_cvo_mapperRepository.findAllByOrchestrationServiceId(os_Id);
        return os_cvo_mapperForDBList;
    }

    @Override
    public String deleteOS_CVO_Mapper(String id) {
        OS_CVO_MapperForDB os_cvo_mapperForDB = os_cvo_mapperRepository.findOne(id);
        os_cvo_mapperRepository.delete(id);
        return "DELETE : " + os_cvo_mapperForDB;
    }
}
