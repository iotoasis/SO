package com.pineone.icbms.so.interfaces.database.logic.itf.mapper;

import com.pineone.icbms.so.interfaces.database.model.mapper.CVO_VO_MapperForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 4..
 */
public interface ICVO_VO_Mapper_DAO {
    //
    CVO_VO_MapperForDB retrieveCVO_VO_Mapper(String id);
    List<CVO_VO_MapperForDB> retrieveCVO_VO_MapperListByCVOID(String cvoId);
    List<CVO_VO_MapperForDB> retrieveCVO_VO_Mapper();
    CVO_VO_MapperForDB createCVO_VO_Mapper(String cvoId, String voId);
    List<CVO_VO_MapperForDB> updateCVO_VO_Mapper(String cvoId, List<String> voIdList);
    String deleteCVO_VO_Mapper(String id);
}
