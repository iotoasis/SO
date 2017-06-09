package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ScheduleContextData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IScheduleContextDAO;
import com.pineone.icbms.so.interfaces.database.model.ScheduleContextForDB;
import com.pineone.icbms.so.interfaces.database.repository.ScheduleContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 30..
 */
@Service
//  ScheduleContext Data Access 기능 구현
public class ScheduleContextDAO implements IScheduleContextDAO {

    @Autowired
    ScheduleContextRepository scheduleContextRepository;

    // ScheduleContext 단일 조회
    @Override
    public ScheduleContextForDB retrieveScheduleContext(String id) {
        return scheduleContextRepository.findOne(id);
    }

    // ScheduleContext 전체 조회
    @Override
    public List<ScheduleContextForDB> retrieveScheduleContextList() {
        return scheduleContextRepository.findAll();
    }

    // ScheduleContext 생성 기능
    @Override
    public String createScheduleContext(ScheduleContextData ScheduleContextData) {
        return null;
    }

    // ScheduleContext 갱신 기능
    @Override
    public String updateScheduleContext(String id, ScheduleContextData ScheduleContextData) {
        return null;
    }

    //  ScheduleContext 삭제 기능
    @Override
    public String deleteScheduleContext(String id) {
        ScheduleContextForDB ScheduleContextForDB = scheduleContextRepository.findOne(id);
        scheduleContextRepository.delete(id);
        String message = "Delete  " + ScheduleContextForDB;
        return message;
    }
}
