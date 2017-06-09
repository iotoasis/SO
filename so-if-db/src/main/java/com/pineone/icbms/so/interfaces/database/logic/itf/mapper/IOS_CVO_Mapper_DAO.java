package com.pineone.icbms.so.interfaces.database.logic.itf.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.OS_CVO_MapperForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 15..
 */
public interface IOS_CVO_Mapper_DAO {
    //
    OS_CVO_MapperForDB retrieveOS_CVO_Mapper(String id);
    List<OS_CVO_MapperForDB> retrieveOS_CVO_MapperListByServiceId(String serviceId);
    List<OS_CVO_MapperForDB> retrieveOS_CVO_Mapper();
    OS_CVO_MapperForDB createOS_CVO_Mapper(String os_Id, String cvo_Id);
    List<OS_CVO_MapperForDB> updateOS_CVO_Mapper(String os_Id, List<String> cvoIdList);
    String deleteOS_CVO_Mapper(String id);
}
