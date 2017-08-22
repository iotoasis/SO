package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jonghee on 2017-06-22.
 */
@Component
public class ContextInformationDao extends AbstractDao {
    //
    public ContextInformationForDB retrieveContextInformation(String id) {
        return null;
    }

    // CI 리스트 조회 기능
    public List<ContextInformationForDB> retrieveContextInformationList() {
        return null;
    }

    // CI 생성 기능
//    public ContextInformationForDB createContextInformation(ContextInformationData contextInformationData) {
//        return null;
//    }

    //  CI 갱신 기능
//    public ContextInformationForDB updateContextInformation(String id, ContextInformationData contextInformationData) {
//        //
//        ContextInformationForDB contextInformationForDB = this.findOne(id);
//        String createdData = contextInformationForDB.getCreated_date();
//        if (contextInformationForDB != null) {
//            contextInformationForDB = updateContextInformationDataConversion(contextInformationData);
//            contextInformationForDB.setId(id);
//            contextInformationForDB.setCreated_date(createdData);
//            this.save(contextInformationForDB);
//        } else {
//            contextInformationForDB = createContextInformation(contextInformationData);
//        }
//        return contextInformationForDB;
//    }

    //  CI 삭제 기능
    public String deleteContextInformation(String id) {
        //
        return null;
    }

//    private ContextInformationForDB createContextInformationDataConversion(ContextInformationData contextInformationData) {
//        ContextInformationForDB contextInformationForDB = new ContextInformationForDB(null,
//                contextInformationData.getName(), contextInformationData.getDescription(), contextInformationData.getUri());
//        return contextInformationForDB;
//    }
//
//    private ContextInformationForDB updateContextInformationDataConversion(ContextInformationData contextInformationData) {
//        //
//        ContextInformationForDB contextInformationForDB = new ContextInformationForDB(null,
//                contextInformationData.getName(), contextInformationData.getDescription(),
//                contextInformationData.getUri(), Calendar.getInstance().getTime());
//        return contextInformationForDB;
//    }

    public ContextInformationForDB findOne(String id) {
        return null;
    }

    public int save(ContextInformationForDB contextInformationForDB) {
        return 0;
    }

    public int delete(String id) {
        return 0;
    }
}
