package com.pineone.icbms.so.scheduler.pr;

import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
import com.pineone.icbms.so.scheduler.logic.SchedulerLogic;
import org.quartz.SchedulerException;
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

    @Autowired
    SchedulerLogic schedulerLogic;

    //NOTE: Profile 을 Scheduler 에 등록
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void registerSchedulerController(@RequestBody ProfileTransFormData profileTransFormData) throws SchedulerException {
        //
        schedulerLogic.registerScheduler(profileTransFormData.getId());
        System.out.println(profileTransFormData.getId());
    }

    //NOTE: Scheduler 시작 -> 시작시 스케줄러 디비에 있는 모든 내용을 이용하여 스케줄러를 실행
    public void startSchedulerController() throws SchedulerException {
        //
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
        schedulerLogic.pauseScheduler();
    }

    //NOTE: 일시정지된 Scheduler 재시작
    @RequestMapping(value = "/restart",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void restartSchedulerController() throws SchedulerException {
        schedulerLogic.restartScheduler();
    }
}
