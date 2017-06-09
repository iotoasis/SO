package com.pineone.icbms.so.interfaces.database.logic.itf.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.OS_VO_MapperForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 12..
 */
public interface IOS_VO_Mapper_DAO {
    // Mapper 단일 조회 기능
    OS_VO_MapperForDB retrieveOS_VO_Mapper(String id);

    List<OS_VO_MapperForDB> retrieveOS_VO_MapperListByServiceId(String serviceId);

    List<OS_VO_MapperForDB> retrieveOS_VO_Mapper();

    OS_VO_MapperForDB createOS_VO_Mapper(String os_Id, String vo_Id);

    List<OS_VO_MapperForDB> updateOS_VO_Mapper(String os_Id, List<String> voIdList);

    String deleteOS_VO_Mapper(String id);
}
