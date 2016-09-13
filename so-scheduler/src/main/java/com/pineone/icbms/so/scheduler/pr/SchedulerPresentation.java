package com.pineone.icbms.so.scheduler.pr;

import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
import com.pineone.icbms.so.profile.proxy.ProfileInternalProxy;
import com.pineone.icbms.so.scheduler.logic.SchedulerLogic;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        schedulerLogic.registerScheduler(profileTransFormData.getId());
    }

    //NOTE: Scheduler 시작 -> 시작시 스케줄러 디비에 있는 모든 내용을 이용하여 스케줄러를 실행
    public void startSchedulerController() throws SchedulerException {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.runScheduler();
    }

//    //NOTE: Scheduler 종료
//    @RequestMapping(value = "/quit",method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    @ResponseBody
//    public void quitSchedulerController() throws SchedulerException {
//        schedulerLogic.quitScheduler();
//    }

    //NOTE: Scheduler 일시 정지
    @RequestMapping(value = "/pause",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void pauseSchedulerController() throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.pauseScheduler();
    }

    //NOTE: 일시정지된 Scheduler 재시작
    @RequestMapping(value = "/restart",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void restartSchedulerController() throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        schedulerLogic.restartScheduler();
    }
}
