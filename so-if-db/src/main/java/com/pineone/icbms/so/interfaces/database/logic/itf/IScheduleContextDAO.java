package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ScheduleContextData;
import com.pineone.icbms.so.interfaces.database.model.ScheduleContextForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IScheduleContextDAO {
    //
    ScheduleContextForDB retrieveScheduleContext(String id);

    List<ScheduleContextForDB> retrieveScheduleContextList();

    String createScheduleContext(ScheduleContextData scheduleContextData);

    String updateScheduleContext(String id, ScheduleContextData scheduleContextData);

    String deleteScheduleContext(String id);
}
