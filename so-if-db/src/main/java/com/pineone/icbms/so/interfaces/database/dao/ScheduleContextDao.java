package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.ScheduleContextForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class ScheduleContextDao extends AbstractDao {
    //
    public ScheduleContextForDB retrieveScheduleContext(String id) {
        return null;
    }

    public List<ScheduleContextForDB> retrieveScheduleContextList() {
        return null;
    }

//    public String createScheduleContext(ScheduleContextData scheduleContextData) {
//        return null;
//    }
//
//    public String updateScheduleContext(String id, ScheduleContextData scheduleContextData) {
//        return null;
//    }

    public String deleteScheduleContext(String id) {
        return null;
    }
}
