package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.AspectData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IAspectDAO;
import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.repository.AspectRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 28..
 */

// Aspect Data Access 로직 구현
@Service
public class AspectDAO implements IAspectDAO {

    @Autowired
    AspectRepository aspectRepository;

    @Autowired
    VirtualObjectDAO virtualObjectDAO;

    //  aspect 단일 조회 기능 구현
    @Override
    public AspectForDB retrieveAspect(String id) {
        return aspectRepository.findOne(id);
    }

    //  Aspect List 조회 기능 구현
    @Override
    public List<AspectForDB> retrieveAspectList() {
        return aspectRepository.findAll();
    }

    // Aspect 저장 기능 구현
    @Override
    public AspectForDB createAspect(AspectData aspectData) {
        AspectForDB aspectForDB = createAspectDataConversion(aspectData);
        aspectForDB.setId("AP-" + IdUtils.createRandomUUID());
        //     virtualObjectForDB.setId((int)virtualObjectRepository.count()+1);
        aspectRepository.save(aspectForDB);
        return aspectForDB;
    }

    //  Aspect 갱신 기능 구현
    @Override
    public AspectForDB updateAspect(String id, AspectData aspectData) {
        //
        AspectForDB aspectForDB = aspectRepository.findOne(id);
        String createDate = aspectForDB.getCreated_date();
        if (aspectForDB != null) {
            aspectForDB = updateAspectDataConversion(aspectData);
            aspectForDB.setId(id);
            aspectForDB.setCreated_date(createDate);
            aspectRepository.save(aspectForDB);
        } else {
            aspectForDB = createAspect(aspectData);
        }

        return aspectForDB;
    }

    // Aspect 삭제 기능 구현
    @Override
    public String deleteAspect(String id) {
        AspectForDB aspectForDB = aspectRepository.findOne(id);
        aspectRepository.delete(id);
        String message = "Delete  " + aspectForDB.toString();
        return message;
    }

    // virtualObject 에 포함되어 있는 Aspect 조회
    @Override
    public AspectForDB retrieveAspectByVirtualObjectId(String virtualObjectId) {
        VirtualObjectForDB virtualObjectForDB = virtualObjectDAO.retrieveVirtualObject(virtualObjectId);
        AspectForDB aspectForDB = aspectRepository.findOne(virtualObjectForDB.getAspectId());
        return aspectForDB;
    }

    private AspectForDB createAspectDataConversion(AspectData aspectData) {
        //
        AspectForDB aspectForDB = new AspectForDB(null, aspectData.getName(), aspectData.getDescription(), aspectData.getUri());
        return aspectForDB;
    }

    private AspectForDB updateAspectDataConversion(AspectData aspectData) {
        //
        AspectForDB aspectForDB = new AspectForDB(null, aspectData.getName(), aspectData.getDescription(), aspectData.getUri(),
                Calendar.getInstance().getTime());
        return aspectForDB;
    }


}
