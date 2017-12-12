package com.pineone.icbms.so.web.interfaces.api;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.ref.ResponseMessage;
import com.pineone.icbms.so.schedule.logic.ISchedulerManager;
import com.pineone.icbms.so.util.conversion.ProfileTransFormData;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 28..
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/scheduler")
public class SchedulerController {

    @Autowired
    ISchedulerManager schedulerManager;

    // Profile 이 등록되면 해당 내용을 Scheduler 에 등록
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerSchedulerController(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        return schedulerManager.registerJob(profileTransFormData.getId(), profileTransFormData.getPeriod());
    }

    // Scheduler 종료
    @RequestMapping(value = "/quit",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage quitSchedulerController() throws SchedulerException {
        //
        return schedulerManager.quitScheduler();
    }

    // 일시 정지된 JobList 재시작 ( ProfileDB에서 Enabled =1 인 profile을 읽어서 scheduler 동작시킴)
    // 메모리와 DB의 싱크를 맞춤
    @RequestMapping(value = "/restart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage restartSchedulerController() throws SchedulerException {
        //
        return schedulerManager.restartJobList();
    }

    // Scheduler 일시 정지  ( ProfileDB의 Enabled = 1 인 모든 profile을 읽어서 scheduler 중지시킴)
    // 메모리상에 돌고있는 스케쥴러를 끔 (DB와 상관없이)
    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage pauseSchedulerController() throws SchedulerException {
    	//
        return schedulerManager.pauseJobList();
    }

    // Job 개별 정지
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage pauseProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        return schedulerManager.pauseJob(profileTransFormData.getId());
    }

    // Job 개별 재시작
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage restartProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        return schedulerManager.restartJob(profileTransFormData.getId());
    }

    // 작동중인 Schedule 목록 조회 (실제 동작 중인 스케쥴 조회)
    // 메모리에서 돌고있는 스케쥴러
    @RequestMapping(value = "/executed", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ProfileForDB> executedScheduleList() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = schedulerManager.executedJobList();
        return scheduledProfileList;
    }
    
    // 작동중인 Schedule 목록 조회 (DB상의 Enabled = 1인값 조회)
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ProfileForDB> retrieveExecuteScheduleList() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = schedulerManager.retrieveExecuteJobList();
        return scheduledProfileList;
    }

    // 대기중인 Schedule 목록 조회 (DB상의 Enabled = 0인값 조회)
    @RequestMapping(value = "/ready", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ProfileForDB> retrieveReadyScheduleList() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = schedulerManager.retrieveReadyJobList();
        return scheduledProfileList;
    }

    // Profile 을 Scheduler 에 등록
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage updateSchedulerPeriod(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        return schedulerManager.updateJob(profileTransFormData.getId(), profileTransFormData.getPeriod());
    }

    // Scheduler 전체 정지 (profile DB 전체 Enabled = 0, scheduler는 그대로 동작)
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage stopSchedulerPeriod() throws SchedulerException {
        //
        return schedulerManager.stopJobListAndChangeStatus();
    }
    
    // profileId를 받아 스케쥴러를 해제 시킴 (DB X)
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage disableSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
    	//
    	return schedulerManager.disableSchedule(profileTransFormData.getId());
    }
    
    
}
