package com.pineone.icbms.so.interfaces.database.logic.mapper;

import com.pineone.icbms.so.interfaces.database.logic.itf.mapper.ICVO_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.mapper.CVO_VO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.mapper.CVO_VO_MapperRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 4..
 */

// Mapper Data Access 기능 구현
@Service
public class CVO_VO_Mapper_DAO implements ICVO_VO_Mapper_DAO {

    @Autowired
    CVO_VO_MapperRepository cvo_vo_mapperRepository;

//    @PersistenceContext
//    EntityManager entityManager;

    // Mapper 단일 조회 기능
    @Override
    public CVO_VO_MapperForDB retrieveCVO_VO_Mapper(String id) {
        return cvo_vo_mapperRepository.findOne(id);
    }

    @Override
    public List<CVO_VO_MapperForDB> retrieveCVO_VO_MapperListByCVOID(String cvoId){
        //
        List<CVO_VO_MapperForDB> cvo_vo_mapperForDBList = cvo_vo_mapperRepository.findAllByCompositeVirtualObjectId(cvoId);
        return cvo_vo_mapperForDBList;
    }

    // Mapper 전체 조회
    @Override
    public List<CVO_VO_MapperForDB> retrieveCVO_VO_Mapper() {
        return cvo_vo_mapperRepository.findAll();
    }

    // Mapper 등록 기능
    @Override
    public CVO_VO_MapperForDB createCVO_VO_Mapper(String cvo_Id, String vo_id) {
        CVO_VO_MapperForDB cvo_vo_mapperForDB
         = new CVO_VO_MapperForDB(cvo_Id, vo_id);
//        String id = String.valueOf(cvo_vo_mapperRepository.findTopByIdByIdDesc());
//        cvo_vo_mapperForDB.setId(id);

//        List<CVO_VO_MapperForDB> cvo_vo_mapperList = cvo_vo_mapperRepository.findAll();
//        String id = String.valueOf(cvo_vo_mapperList.size() + 1);

//        long id = entityManager.createNamedQuery("findRecentCVOVOMapper", CVO_VO_MapperForDB.class)
//                .getSingleResult().getId() + 1;

        String cvo_vo_mapperID = "CVM-" + IdUtils.createRandomUUID();
        cvo_vo_mapperForDB.setId(cvo_vo_mapperID);
        cvo_vo_mapperRepository.save(cvo_vo_mapperForDB);
        return cvo_vo_mapperForDB;
    }

//     Mapper 갱신 기능
    @Override
    public List<CVO_VO_MapperForDB> updateCVO_VO_Mapper(String cvoId, List<String> voIdList ) {
        //
        List<CVO_VO_MapperForDB> cvoVoMapperForDBList = cvo_vo_mapperRepository.findAllByCompositeVirtualObjectId(cvoId);

        //기존 값 삭제
        for(CVO_VO_MapperForDB cvo_vo_mapperForDB : cvoVoMapperForDBList){
            deleteCVO_VO_Mapper(cvo_vo_mapperForDB.getId());
        }
//        cvoVoMapperForDBList.clear();


        for(String voId : voIdList) {
            CVO_VO_MapperForDB cvo_vo_mapperForDB = new CVO_VO_MapperForDB(cvoId, voId);
            String cvo_vo_mapperID = "CVM-" + IdUtils.createRandomUUID();
            cvo_vo_mapperForDB.setId(cvo_vo_mapperID);

//                createCVO_VO_Mapper(cvoId, voId);
//                CVO_VO_MapperForDB cvo_vo_mapperForDB
//                        = new CVO_VO_MapperForDB(cvoId, voId);
//                String id = entityManager.createNamedQuery("findRecentCVOVOMapper", CVO_VO_MapperForDB.class)
//                        .getSingleResult().getId();
//                long number = (Long.parseLong(id)+1);
//                cvo_vo_mapperForDB.setId(String.valueOf(number));

            cvo_vo_mapperRepository.save(cvo_vo_mapperForDB);
//            cvoVoMapperForDBList.add(cvo_vo_mapperForDB);
        }

        cvoVoMapperForDBList = cvo_vo_mapperRepository.findAllByCompositeVirtualObjectId(cvoId);

        return cvoVoMapperForDBList;
    }
////
    // Mapper 삭제 기능
    @Override
    public String deleteCVO_VO_Mapper(String id) {
        //
        CVO_VO_MapperForDB cvo_vo_mapperForDB = cvo_vo_mapperRepository.findOne(id);
        cvo_vo_mapperRepository.delete(cvo_vo_mapperForDB);
        return "DELETE : " + cvo_vo_mapperForDB;
    }
//
//
////
//    private CVO_VO_MapperForDB updateCvoVoMapperDataConversion(String cvo_id, String vo_id) {
//        Date date = Calendar.getInstance().getTime();
//        String dateString = DateFormat.dateFormat(date);
//        CVO_VO_MapperForDB cvo_vo_mapperForDB = new CVO_VO_MapperForDB(cvo_id, vo_id, dateString);
//        return cvo_vo_mapperForDB;
//    }
}
