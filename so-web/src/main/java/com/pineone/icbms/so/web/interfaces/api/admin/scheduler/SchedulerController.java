package com.pineone.icbms.so.web.interfaces.api.admin.scheduler;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.ProfileTransFormData;
import com.pineone.icbms.so.interfaces.database.logic.ProfileDAO;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.schedule.logic.SchedulerManager;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2017. 6. 7..
 */

@RestController
@RequestMapping("/scheduler")
@ResponseStatus(value = HttpStatus.OK)
public class SchedulerController {
    //
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * aspect repository(DAO)
     */
    @Autowired
    private SchedulerManager schedulerManager;

    @Autowired
    private ProfileDAO profileDAO;


    // 구동중이던 모든 job 정지  =  스케줄러 정지
    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void pauseSchedulerController() throws SchedulerException {
        log.info("]]]]]]]]]] Scheduler Pause");
        schedulerManager.pauseJobList();
    }

    // 일시 정지되었던 모든 job 재시작 = 스케줄러 재시작
    @RequestMapping(value = "/restart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void restartSchedulerController() throws SchedulerException {
        log.info("]]]]]]]]]] Scheduler Restart");
        schedulerManager.restartJobList();
    }

    // 지정한 하나의 job 정지
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void pauseProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        log.info("]]]]]]]]]] Specific Job Pause : " + profileTransFormData.getId());
        schedulerManager.pauseJob(profileTransFormData.getId());
    }

    // 지정한 하나의 job 재시작
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void restartProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        log.info("]]]]]]]]]] Specific Job Restart" + profileTransFormData.getId());
        schedulerManager.restartJob(profileTransFormData.getId());
    }

    // 작동중인 Job 목록 조회
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ProfileForDB> retrieveExecuteScheduleList() throws SchedulerException {
        log.info("]]]]]]]]]] Executing Job list");
        List<ProfileForDB> scheduledProfileList = schedulerManager.retrieveExecuteJobList();
        return scheduledProfileList;
    }

    // 대기중인 Schedule 목록 조회
    @RequestMapping(value = "/ready", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ProfileForDB> retrieveReadyScheduleList() throws SchedulerException {
        log.info("]]]]]]]]]] Not Executing Job List");
        List<ProfileForDB> scheduledProfileList = schedulerManager.retrieveReadyJobList();
        return scheduledProfileList;
    }

    // 모든 Schedule 목록 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ProfileForDB> retrieveAllSchedulerLIst() throws SchedulerException {
        //
        log.info("]]]]]]]]]] Retrieve All Job List");
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileList();
        return scheduledProfileList;
    }

    // Job 주기 Update
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void updateSchedulerPeriod(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        log.info("]]]]]]]]]] update Job : " + profileTransFormData.getId());
        schedulerManager.updateJob(profileTransFormData.getId(), profileTransFormData.getPeriod());
    }

    // Scheduler 전체 정지 (일시 정지 아님)
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void stopSchedulerPeriod() throws SchedulerException {
        //
        log.info("]]]]]]]]]] All Job Stop (Not pause = Change Status)");
        schedulerManager.stopAllJobListAndChangeStatus();
    }

    // Scheduler 전체 시작 ( 모든 스케줄러 시작 - Status 값 변경)
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void startSchedulerPeriod() throws SchedulerException {
        //
        log.info("]]]]]]]]]] All Job Start (Not Restart = change Status");
        schedulerManager.startAllJobListAndChangeStatus();

    }
}
