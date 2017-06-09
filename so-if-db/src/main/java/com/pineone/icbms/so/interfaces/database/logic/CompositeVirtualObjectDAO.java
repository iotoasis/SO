package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.CompositeVirtualObjectData;
import com.pineone.icbms.so.interfaces.database.logic.itf.ICompositeVirtualObjectDAO;
import com.pineone.icbms.so.interfaces.database.logic.mapper.CVO_VO_Mapper_DAO;
import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.mapper.CVO_VO_MapperForDB;
import com.pineone.icbms.so.interfaces.database.repository.CompositeVirtualObjectRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */

// CVO Data Access 로직 구현
@Service
public class CompositeVirtualObjectDAO implements ICompositeVirtualObjectDAO {
    //
    @Autowired
    CompositeVirtualObjectRepository compositeVirtualObjectRepository;

    @Autowired
    CVO_VO_Mapper_DAO cvo_vo_mapper_dao;

    @Autowired
    DataBaseStoreDAO dataBaseStoreDAO;

    //  단일 CVO 조회 기능
    @Override
    public CompositeVirtualObjectForDB retrieveCompositeVirtualObject(String id){
        //
         CompositeVirtualObjectForDB compositeVirtualObjectForDB = compositeVirtualObjectRepository.findOne(id);
         List<VirtualObjectForDB> virtualObjectForDBList = dataBaseStoreDAO.getVirtualObjectListByCompositeVirtualObjectId(id);
         compositeVirtualObjectForDB.setVirtualObjectForDBList(virtualObjectForDBList);
         return compositeVirtualObjectForDB;
    }

    //  CVO List 조회
    @Override
    public List<CompositeVirtualObjectForDB> retrieveCompositeVirtualObjectList() {
        //
        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList = compositeVirtualObjectRepository.findAll();
        for(CompositeVirtualObjectForDB compositeVirtualObjectForDB : compositeVirtualObjectForDBList){
            compositeVirtualObjectForDB.setVirtualObjectForDBList(dataBaseStoreDAO.getVirtualObjectListByCompositeVirtualObjectId(
                    compositeVirtualObjectForDB.getId()
            ));
        }
        return compositeVirtualObjectForDBList;
    }

    //  CVO 생성 기능
    @Override
    public CompositeVirtualObjectForDB createCompositeVirtualObject(CompositeVirtualObjectData compositeVirtualObjectData) {
        //
        CompositeVirtualObjectForDB compositeVirtualObjectForDB =
                createCompositeVirtualObjectDataConversion(compositeVirtualObjectData);

        String cvoId = "CVO-" + IdUtils.createRandomUUID();
        compositeVirtualObjectForDB.setId(cvoId);

        System.out.println(cvoId);
        System.out.println(compositeVirtualObjectData.getVirtualObjectIdList());

        List<String> voIdList = compositeVirtualObjectData.getVirtualObjectIdList();
//        System.out.println("********************aa" + voIdList);
//        for(String voId : voIdList){
//            cvo_vo_mapper_dao.createCVO_VO_Mapper(cvoId, voId);
//        }

        for(String voId : voIdList){
            cvo_vo_mapper_dao.createCVO_VO_Mapper(cvoId, voId);
        }

        compositeVirtualObjectForDB.setVirtualObjectForDBList(dataBaseStoreDAO.getVirtualObjectListByCompositeVirtualObjectId(cvoId));
        System.out.println(compositeVirtualObjectForDB.toString());



//        for (String voId : voIdList) {
//            cvo_vo_mapperRepository.save(new CVO_VO_MapperForDB(3,cvoId, voId));
//
////            VirtualObjectForDB virtualObjectForDB = virtualObjectDAO.retrieveVirtualObject(voIdList.get(a));
////            virtualObjectForDBList.add(virtualObjectForDB);
//        }


//        String id = entityManager.createNamedQuery("findRecentCompositeVirtualObject", CompositeVirtualObjectForDB.class)
//                .getSingleResult().getId();
//        compositeVirtualObjectForDB.setId(id+1);


        compositeVirtualObjectRepository.save(compositeVirtualObjectForDB);
        //     virtualObjectForDB.setId((int)virtualObjectRepository.count()+1);

//        System.out.println("******************save" + compositeVirtualObjectForDB.toString());
//        return "Create  " + compositeVirtualObjectForDB.toString();
        return compositeVirtualObjectForDB;
    }

    // CVO 갱신 기능
    @Override
    public CompositeVirtualObjectForDB updateCompositeVirtualObject(String id, CompositeVirtualObjectData compositeVirtualObjectData) {
        //
        CompositeVirtualObjectForDB compositeVirtualObjectForDB;
        if (compositeVirtualObjectRepository.findOne(id) != null) {
//            compositeVirtualObjectForDB = compositeVirtualObjectRepository.findOne(id);

            //새로운 cvo 값 셋팅
            compositeVirtualObjectForDB = updateCompositeVirtualObjectDataConversion(compositeVirtualObjectData);
            compositeVirtualObjectForDB.setId(id);
            compositeVirtualObjectForDB.setCreated_date(compositeVirtualObjectRepository.findOne(id).getCreated_date());
            List<String> voIdList = compositeVirtualObjectData.getVirtualObjectIdList();

            cvo_vo_mapper_dao.updateCVO_VO_Mapper(id, voIdList);


//            voIdList = cvo_vo_mapper_dao.updateCVO_VO_Mapper(id, voIdList);
//            System.out.println("********************aa" + voIdList);


//            for (int a = 0; a < voIdList.size(); a++) {
//                cvo_vo_mapperRepository.save(new CVO_VO_MapperForDB(id, voIdList.get(a)));
//            }
            compositeVirtualObjectRepository.delete(id);
            compositeVirtualObjectForDB.setVirtualObjectForDBList(dataBaseStoreDAO.getVirtualObjectListByCompositeVirtualObjectId(id));
            compositeVirtualObjectRepository.save(compositeVirtualObjectForDB);
//            System.out.println("******************save" + compositeVirtualObjectForDB.toString());
//            return "Update" + compositeVirtualObjectForDB.toString();
        } else {
            compositeVirtualObjectForDB = createCompositeVirtualObject(compositeVirtualObjectData);
        }

        return compositeVirtualObjectForDB;
    }

    // CVO 삭제 기능
    @Override
    public String deleteCompositeVirtualObject(String id) {
        //
        CompositeVirtualObjectForDB compositeVirtualObjectForDB = compositeVirtualObjectRepository.findOne(id);
        List<CVO_VO_MapperForDB> cvoVoMapperForDBList = cvo_vo_mapper_dao.retrieveCVO_VO_MapperListByCVOID(id);

        //기존 값 삭제
        for(CVO_VO_MapperForDB cvo_vo_mapperForDB : cvoVoMapperForDBList){
            compositeVirtualObjectForDB.getId();

            cvo_vo_mapper_dao.deleteCVO_VO_Mapper(cvo_vo_mapperForDB.getId());
        }
        compositeVirtualObjectRepository.delete(id);
        String message = "Delete  " + compositeVirtualObjectForDB.toString();
        return message;
    }

    CompositeVirtualObjectForDB createCompositeVirtualObjectDataConversion(CompositeVirtualObjectData compositeVirtualObjectData) {

//        CompositeVirtualObjectForDB compositeVirtualObjectForDB = new CompositeVirtualObjectForDB(compositeVirtualObjectData.getName(),
//                compositeVirtualObjectData.getFunctionality_id(), compositeVirtualObjectData.getAspect_id(), compositeVirtualObjectData.getType(),
//               compositeVirtualObjectData.getDescription());
//        for(VirtualObjectForDB virtualObjectForDB  compositeVirtualObjectData.getVirtualObjectForDBList()){
//            compositeVirtualObjectForDB.addVirtualObject(virtualObjectForDB);
//        }

        CompositeVirtualObjectForDB compositeVirtualObjectForDB = new CompositeVirtualObjectForDB(null,
                compositeVirtualObjectData.getName(), compositeVirtualObjectData.getDescription(),
                compositeVirtualObjectData.getFunctionality_id(), compositeVirtualObjectData.getAspect_id(),
                compositeVirtualObjectData.getType());
        return compositeVirtualObjectForDB;
    }

    private CompositeVirtualObjectForDB updateCompositeVirtualObjectDataConversion(CompositeVirtualObjectData compositeVirtualObjectData) {
        //
        CompositeVirtualObjectForDB compositeVirtualObjectForDB = new CompositeVirtualObjectForDB(null,
                compositeVirtualObjectData.getName(), compositeVirtualObjectData.getDescription(),
                compositeVirtualObjectData.getFunctionality_id(), compositeVirtualObjectData.getAspect_id(),
                compositeVirtualObjectData.getType(), Calendar.getInstance().getTime());
        return compositeVirtualObjectForDB;
    }
}

