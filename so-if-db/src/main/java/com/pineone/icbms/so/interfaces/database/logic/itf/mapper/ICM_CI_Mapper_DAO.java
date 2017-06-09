package com.pineone.icbms.so.interfaces.database.logic.itf.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.CM_CI_MapperForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 11..
 */
public interface ICM_CI_Mapper_DAO {
    //
    CM_CI_MapperForDB retrieveCM_CI_MapperByMapperId(String mapperId);
    List<CM_CI_MapperForDB> retrieveCM_CI_MapperListBYCMId(String cmId);
    List<CM_CI_MapperForDB> retrieveCM_CI_Mapper();
    CM_CI_MapperForDB createCM_CI_Mapper(String cmId, String ciID);
    List<CM_CI_MapperForDB> updateCM_CI_Mapper(String cmId, List<String> ciIdList);
    String deleteCM_CI_Mapper(String id);
}
