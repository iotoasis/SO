package com.pineone.icbms.so.interfaces.database.logic.mapper;

import com.pineone.icbms.so.interfaces.database.logic.itf.mapper.IOS_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.mapper.OS_VO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.mapper.OS_VO_MapperRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 12..
 */
@Service
public class OS_VO_Mapper_DAO implements IOS_VO_Mapper_DAO{
    //
    @Autowired
    OS_VO_MapperRepository os_vo_mapperRepository;

    // Mapper 단일 조회 기능
    @Override
    public OS_VO_MapperForDB retrieveOS_VO_Mapper(String id){
        return os_vo_mapperRepository.findOne(id);
    }

    @Override
    public List<OS_VO_MapperForDB> retrieveOS_VO_MapperListByServiceId(String serviceId){
        //
        List<OS_VO_MapperForDB> os_vo_mapperForDBList = os_vo_mapperRepository.findAllByOrchestrationServiceId(serviceId);
        return os_vo_mapperForDBList;
    }

    @Override
    public List<OS_VO_MapperForDB> retrieveOS_VO_Mapper(){
        return os_vo_mapperRepository.findAll();
    }

    @Override
    public OS_VO_MapperForDB createOS_VO_Mapper(String os_Id, String vo_Id){
        //
        OS_VO_MapperForDB os_vo_mapperForDB = new OS_VO_MapperForDB(os_Id, vo_Id);

        String os_vo_mapperId = "OVM-" + IdUtils.createRandomUUID();
        os_vo_mapperForDB.setId(os_vo_mapperId);
        os_vo_mapperRepository.save(os_vo_mapperForDB);
        return os_vo_mapperForDB;
    }

    @Override
    public List<OS_VO_MapperForDB> updateOS_VO_Mapper(String os_Id, List<String> voIdList){
        //
        List<OS_VO_MapperForDB> os_vo_mapperForDBList = os_vo_mapperRepository.findAllByOrchestrationServiceId(os_Id);
        for(OS_VO_MapperForDB os_vo_mapperForDB : os_vo_mapperForDBList){
            deleteOS_VO_Mapper(os_vo_mapperForDB.getId());
        }
        for(String voId : voIdList){
            OS_VO_MapperForDB os_vo_mapperForDB = new OS_VO_MapperForDB(os_Id, voId);
            String os_vo_mapperID = "OVM-" + IdUtils.createRandomUUID();
            os_vo_mapperForDB.setId(os_vo_mapperID);

            os_vo_mapperRepository.save(os_vo_mapperForDB);
        }

        os_vo_mapperForDBList = os_vo_mapperRepository.findAllByOrchestrationServiceId(os_Id);
        return os_vo_mapperForDBList;
    }

    @Override
    public String deleteOS_VO_Mapper(String id) {
        //
        OS_VO_MapperForDB os_vo_mapperForDB = os_vo_mapperRepository.findOne(id);
        os_vo_mapperRepository.delete(id);
        return "DELETE : " + os_vo_mapperForDB;
    }
}
