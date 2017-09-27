package com.pineone.icbms.so.web.interfaces.api.service.scheduler;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
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
@RequestMapping(value = "/scheduler")
public class SchedulerController {

    @Autowired
    ISchedulerManager schedulerManager;

    // Profile 이 등록되면 해당 내용을 Scheduler 에 등록
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String registerSchedulerController(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        schedulerManager.registerJob(profileTransFormData.getId(), profileTransFormData.getPeriod());
        return "Register-Job OK : " +profileTransFormData.getId()+"-"+profileTransFormData.getPeriod();
    }

    // Scheduler 종료
    @RequestMapping(value = "/quit",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String quitSchedulerController() throws SchedulerException {
        //
        schedulerManager.quitScheduler();
        return "Quit-Scheduler OK";
    }

    // 일시 정지된 JobList 재시작
    @RequestMapping(value = "/restart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String restartSchedulerController() throws SchedulerException {
        //
        schedulerManager.restartJobList();
        return "Restart-Job-List OK";
    }

    // Scheduler 일시 정지
    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String pauseSchedulerController() throws SchedulerException {
        schedulerManager.pauseJobList();
        return "Pause-Job-List OK";
   }
    // Job 개별 정지
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String pauseProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        schedulerManager.pauseJob(profileTransFormData.getId());
        return "Pause-Job OK";
    }

    // Job 개별 재시작
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String restartProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        schedulerManager.restartJob(profileTransFormData.getId());
        return "Restar-Job OK";
    }

    // 작동중인 Schedule 목록 조회
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String retrieveExecuteScheduleList() throws SchedulerException {
        //
    	String strRet=null;
        List<ProfileForDB> scheduledProfileList = schedulerManager.retrieveExecuteJobList();
        if(scheduledProfileList.isEmpty()) return "Data Not Found.";
        else strRet= scheduledProfileList.toString();
        return strRet;
    }

    // 대기중인 Schedule 목록 조회
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
    public String updateSchedulerPeriod(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        schedulerManager.updateJob(profileTransFormData.getId(), profileTransFormData.getPeriod());
        return "updateSchedulerPeriod : " + profileTransFormData.getId()+" | "+profileTransFormData.getPeriod();
    }

    // Scheduler 전체 정지 (일시 정지 아님)
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String stopSchedulerPeriod() throws SchedulerException {
        //
        schedulerManager.stopJobListAndChangeStatus();
        return "stop-Scheduler-Period";
    }

}
