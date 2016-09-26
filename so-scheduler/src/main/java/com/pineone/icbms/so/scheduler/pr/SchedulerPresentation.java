package com.pineone.icbms.so.scheduler.pr;

import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.logic.SchedulerLogic;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 9. 5..
 */

@Controller
@RequestMapping(value = "/scheduler")
public class SchedulerPresentation {

    public static final Logger logger = LoggerFactory.getLogger(SchedulerPresentation.class);

    @Autowired
    SchedulerLogic schedulerLogic;

    //NOTE: Profile 을 Scheduler 에 등록
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void registerSchedulerController(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        logger.info(LogPrint.outputInfoLogPrint() + ", ProfileId = " + profileTransFormData.getId());
        logger.debug("Profile = " + profileTransFormData.toString());
        schedulerLogic.registerScheduler(profileTransFormData.getId(), profileTransFormData.getPeriod());
    }
//
//    //NOTE: Scheduler 시작 -> 시작시 스케줄러 디비에 있는 모든 내용을 이용하여 스케줄러를 실행
//    public void startSchedulerController() throws SchedulerException {
//        //
//        logger.info(LogPrint.outputInfoLogPrint());
//        schedulerLogic.();
//    }

    //NOTE: Scheduler 종료
    @RequestMapping(value = "/quit",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void quitSchedulerController() throws SchedulerException {
        schedulerLogic.quitScheduler();
    }

    //NOTE: Scheduler 일시 정지
    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void pauseSchedulerController() throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.pauseScheduler();
    }

    //NOTE: 일시정지된 Scheduler 재시작
    @RequestMapping(value = "/restart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void restartSchedulerController() throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.restartScheduler();
    }

    //NOTE: Scheduler 개별 일시 정지
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void pauseProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.pauseProfileScheduler(profileTransFormData.getId());
    }

    //NOTE: Scheduler 개별 일시 정지
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void restartProfileSchedule(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.restartProfileScheduler(profileTransFormData.getId());
    }

    //NOTE: 작동중인 Schedule 목록 조회
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ScheduledProfile> retrieveExecuteScheduleList() throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        List<ScheduledProfile> scheduledProfileList = schedulerLogic.retrieveExecuteScheduleList();
        return scheduledProfileList;
    }

    //NOTE: 대기중인 Schedule 목록 조회
    @RequestMapping(value = "/ready", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ScheduledProfile> retrieveReadyScheduleList() throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        List<ScheduledProfile> scheduledProfileList = schedulerLogic.retrieveReadyScheduleList();
        return scheduledProfileList;
    }

    //NOTE: 모든 Schedule 목록 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ScheduledProfile> retrieveAllSchedulerLIst() throws SchedulerException {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        List<ScheduledProfile> scheduledProfileList = schedulerLogic.retrieveSchedulerList();
        return scheduledProfileList;
    }

    //NOTE: Profile 을 Scheduler 에 등록
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void updateSchedulerPeriod(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        logger.info(LogPrint.outputInfoLogPrint() + ", ProfileId = " + profileTransFormData.getId());
        logger.debug("Profile = " + profileTransFormData.toString());
        schedulerLogic.updateScheduler(profileTransFormData.getId(), profileTransFormData.getPeriod());
    }
}
