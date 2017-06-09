package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.FunctionalityData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IFunctionalityDAO;
import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import com.pineone.icbms.so.interfaces.database.repository.FunctionalityRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by melvin on 2017. 3. 30..
 */
@Service
//  Functionality Data Access 기능 구현
public class FunctionalityDAO implements IFunctionalityDAO {

    @Autowired
    FunctionalityRepository functionalityRepository;

    // Functionality 단일 조회
    @Override
    public FunctionalityForDB retrieveFunctionality(String id) {
        return functionalityRepository.findOne(id);
    }

    // Functionality 전체 조회
    @Override
    public List<FunctionalityForDB> retrieveFunctionalityList() {
        return functionalityRepository.findAll();
    }

    // Functionality 생성 기능
    @Override
    public FunctionalityForDB createFunctionality(FunctionalityData functionalityData) {
        //
        FunctionalityForDB functionalityForDB = createFunctionalityDataConversion(functionalityData);
        functionalityForDB.setId("FC" + IdUtils.createRandomUUID());
        functionalityRepository.save(functionalityForDB);
        return functionalityForDB;
    }

    // Functionality 갱신 기능
    @Override
    public FunctionalityForDB updateFunctionality(String id, FunctionalityData functionalityData) {
        //
        FunctionalityForDB functionalityForDB = functionalityRepository.findOne(id);
        String createDate = functionalityForDB.getCreated_date();
        if (functionalityForDB != null) {
            functionalityForDB = updateFuncionalityDataConversion(functionalityData);
            functionalityForDB.setId(id);
            functionalityForDB.setCreated_date(createDate);
            functionalityRepository.save(functionalityForDB);
        } else {
            functionalityForDB = createFunctionality(functionalityData);
        }
        return functionalityForDB;
    }

    //  Functionality 삭제 기능
    @Override
    public String deleteFunctionality(String id) {
        FunctionalityForDB functionalityForDB = functionalityRepository.findOne(id);
        functionalityRepository.delete(id);
        String message = "Delete  " + functionalityForDB;
        return message;
    }

    private FunctionalityForDB createFunctionalityDataConversion(FunctionalityData functionalityData) {
        //
        FunctionalityForDB functionalityForDB = new FunctionalityForDB(null, functionalityData.getName(),
                functionalityData.getDescription(), functionalityData.getUri());
        return functionalityForDB;
    }

    private FunctionalityForDB updateFuncionalityDataConversion(FunctionalityData functionalityData) {
        //
        FunctionalityForDB functionalityForDB = new FunctionalityForDB(null, functionalityData.getName(),
                functionalityData.getDescription(), functionalityData.getUri(), Calendar.getInstance().getTime());
        return functionalityForDB;
    }
}
