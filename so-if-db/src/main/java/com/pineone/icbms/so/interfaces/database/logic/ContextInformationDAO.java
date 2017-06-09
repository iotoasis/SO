package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ContextInformationData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IContextInformationDAO;
import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
import com.pineone.icbms.so.interfaces.database.repository.ContextInformationRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */

// CI Data Access 로직 구현
@Service
public class ContextInformationDAO implements IContextInformationDAO {

    @Autowired
    ContextInformationRepository contextInformationRepository;

    // 단일 CI 조회 기능
    @Override
    public ContextInformationForDB retrieveContextInformation(String id) {
        //
        return contextInformationRepository.findOne(id);
    }

    // CI 리스트 조회 기능
    @Override
    public List<ContextInformationForDB> retrieveContextInformationList() {
        //
        return contextInformationRepository.findAll();
    }

    // CI 생성 기능
    @Override
    public ContextInformationForDB createContextInformation(ContextInformationData contextInformationData) {
        //
        ContextInformationForDB contextInformationForDB = createContextInformationDataConversion(contextInformationData);
        contextInformationForDB.setId("CI" + IdUtils.createRandomUUID());
        //     virtualObjectForDB.setId((int)virtualObjectRepository.count()+1);
        contextInformationRepository.save(contextInformationForDB);
        return contextInformationForDB;
    }

    //  CI 갱신 기능
    @Override
    public ContextInformationForDB updateContextInformation(String id, ContextInformationData contextInformationData) {
        //
        ContextInformationForDB contextInformationForDB = contextInformationRepository.findOne(id);
        String createdData = contextInformationForDB.getCreated_date();
        if (contextInformationForDB != null) {
            contextInformationForDB = updateContextInformationDataConversion(contextInformationData);
            contextInformationForDB.setId(id);
            contextInformationForDB.setCreated_date(createdData);
            contextInformationRepository.save(contextInformationForDB);
        } else {
            contextInformationForDB = createContextInformation(contextInformationData);
        }
        return contextInformationForDB;
    }

    //  CI 삭제 기능
    @Override
    public String deleteContextInformation(String id) {
        //
        ContextInformationForDB contextInformationForDB = contextInformationRepository.findOne(id);
        contextInformationRepository.delete(id);
        String message = "Delete  " + contextInformationForDB.toString();
        return message;
    }

    private ContextInformationForDB createContextInformationDataConversion(ContextInformationData contextInformationData) {
        ContextInformationForDB contextInformationForDB = new ContextInformationForDB(null,
                contextInformationData.getName(), contextInformationData.getDescription(), contextInformationData.getUri());
        return contextInformationForDB;
    }

    private ContextInformationForDB updateContextInformationDataConversion(ContextInformationData contextInformationData) {
        //
        ContextInformationForDB contextInformationForDB = new ContextInformationForDB(null,
                contextInformationData.getName(), contextInformationData.getDescription(),
                contextInformationData.getUri(), Calendar.getInstance().getTime());
        return contextInformationForDB;
    }
}
