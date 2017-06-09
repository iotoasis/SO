package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.VirtualObjectData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IVirtualObjectDAO;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.repository.VirtualObjectRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 27..
 */

// VirtualObject Data Access 로직 구현
@Service
public class VirtualObjectDAO implements IVirtualObjectDAO {

    @Autowired
    VirtualObjectRepository virtualObjectRepository;

    @Autowired
    FunctionalityDAO functionalityDAO;

    @Autowired
    AspectDAO aspectDAO;

    //  VO 단일 조회 기능 구현
    @Override
    public VirtualObjectForDB retrieveVirtualObject(String id) {
        return virtualObjectRepository.findById(id);
    }

    //  VO List 조회 기능 구현
    @Override
    public List<VirtualObjectForDB> retrieveVirtualObjectList() {
        return virtualObjectRepository.findAll();
    }

    // VO 저장 기능 구현
    @Override
    public VirtualObjectForDB createVirtualObject(VirtualObjectData virtualObjectData) {
        VirtualObjectForDB virtualObjectForDB = createVirtualObjectDataConversion(virtualObjectData);
//        String id = entityManager.createNamedQuery("findRecentVirtualObject", VirtualObjectForDB.class)
//                .getSingleResult().getId();
        virtualObjectForDB.setId("VO" + IdUtils.createRandomUUID());
        //     virtualObjectForDB.setId((int)virtualObjectRepository.count()+1);
        virtualObjectRepository.save(virtualObjectForDB);
        //return "Create  " + virtualObjectForDB.toString();
        return virtualObjectForDB;
    }

    //  VO 갱신 기능 구현
    @Override
    public VirtualObjectForDB updateVirtualObject(String id, VirtualObjectData virtualObjectData) {

        if (virtualObjectRepository.findOne(id) != null) {
            VirtualObjectForDB virtualObjectForDB = virtualObjectRepository.findById(id);
            virtualObjectForDB = updateVirtualObjectDataConversion(virtualObjectData);
            virtualObjectForDB.setId(id);
            virtualObjectForDB.setCreated_date(virtualObjectRepository.findOne(id).getCreated_date());
            virtualObjectRepository.save(virtualObjectForDB);
            //return "Update  " + virtualObjectForDB.toString();
            return virtualObjectForDB;
        } else {
            VirtualObjectForDB virtualObjectForDB = createVirtualObject(virtualObjectData);
            //return createMessage;
            return virtualObjectForDB;
        }
    }

    // VO 삭제 기능 구현
    @Override
    public String deleteVirtualObject(String id) {
        VirtualObjectForDB virtualObjectForDB = virtualObjectRepository.findById(id);
        virtualObjectRepository.delete(id);
        String message = "Delete  " + virtualObjectForDB.toString();
        return message;
    }

    public VirtualObjectForDB createVirtualObjectDataConversion(VirtualObjectData virtualObjectData) {
//        FunctionalityForDB functionalityForDB = functionalityDAO.retrieveFunctionality(virtualObjectData.getFunctionality_id());
//        AspectForDB aspectForDB = aspectDAO.retrieveAspect(virtualObjectData.getAspect_id());
//        VirtualObjectForDB virtualObjectForDB = new VirtualObjectForDB(virtualObjectData.getName(), functionalityForDB,
//                aspectForDB, virtualObjectData.getDescription());

        VirtualObjectForDB virtualObjectForDB = new VirtualObjectForDB(null, virtualObjectData.getName(),
                virtualObjectData.getDescription(), virtualObjectData.getFunctionality_id(),
                virtualObjectData.getAspect_id());
        return virtualObjectForDB;
    }

    public VirtualObjectForDB updateVirtualObjectDataConversion(VirtualObjectData virtualObjectData) {

//        virtualObjectData.setModified_date(Calendar.getInstance().getTime());
//        FunctionalityForDB functionalityForDB = functionalityDAO.retrieveFunctionality(virtualObjectData.getFunctionality_id());
//        AspectForDB aspectForDB = aspectDAO.retrieveAspect(virtualObjectData.getAspect_id());
//        VirtualObjectForDB virtualObjectForDB = new VirtualObjectForDB(virtualObjectData.getName(), functionalityForDB,
//                aspectForDB, virtualObjectData.getDescription(), virtualObjectData.getModified_date());

        VirtualObjectForDB virtualObjectForDB = new VirtualObjectForDB(null, virtualObjectData.getName(),
                virtualObjectData.getDescription(), virtualObjectData.getFunctionality_id(),
                virtualObjectData.getAspect_id(), virtualObjectData.getModified_date());
        return virtualObjectForDB;
    }
}
