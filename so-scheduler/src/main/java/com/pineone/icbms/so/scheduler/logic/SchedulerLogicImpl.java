package com.pineone.icbms.so.scheduler.logic;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.proxy.SchedulerProxy;
import com.pineone.icbms.so.scheduler.store.SchedulerStore;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by melvin on 2016. 9. 5..
 */

@Service
public class SchedulerLogicImpl implements SchedulerLogic, Runnable{

    public static final Logger logger = LoggerFactory.getLogger(SchedulerLogicImpl.class);

    @Autowired
    SchedulerProxy schedulerProxy;

    @Autowired
    SchedulerStore schedulerStore;

    private Thread thread;

    String groupName = "soGroup";
    Scheduler scheduler;

    //NOTE : Properties 내용이 false 이면 스케줄러 동작하지 않음 (테스트때 사용)
    private String getProperties(){
        Properties properties = new Properties();
        InputStream in = SchedulerLogicImpl.class.getClassLoader().getResourceAsStream("scheduler.properties");
        String executeScheduler = null;
        try {
            properties.load(in);
            executeScheduler = properties.getProperty("schedule.state");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return executeScheduler;
    }

    //NOTE : SO 구동시 스케줄러 DB의 내용을 스케줄러에 올려 실행하기 위한 쓰레드 생성
    public SchedulerLogicImpl() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if(thread == null){
            thread = new Thread(this);
        }
        if(!thread.isAlive()){
            thread.start();
        }
    }

    //NOTE : 구동되고 있는 스케줄러에 새로운 스케줄 내용 등록
    @Override
    public void registerScheduler(String profileId, int period) throws SchedulerException {


        logger.debug("ProfileId = " + profileId + " ,Period = " + period);
        ScheduledProfile scheduledProfile = new ScheduledProfile(profileId, period);
        logger.debug("ScheduledProfile = " + scheduledProfile.toString());

//        schedulerStore.createScheduledProfile(scheduledProfile);

        //NOTE: 스케줄러 DB 가 비어있거나
        if(schedulerStore.retrieveScheduledProfile().isEmpty()){
            schedulerStore.createScheduledProfile(scheduledProfile);
            schedulerStore.updateStatus(scheduledProfile, 1);

        }else {
            //NOTE: 스케줄러 내용이 중복되지 않을때
            if(!(schedulerStore.isExistScheduledProfile(scheduledProfile.getId()))){
                schedulerStore.createScheduledProfile(scheduledProfile);
                schedulerStore.updateStatus(scheduledProfile, 1);
            }
        }

        //NOTE: 스케줄러 생성
        JobDetail job = JobBuilder.newJob(ScheduleNotificationManager.class)
                .withIdentity(scheduledProfile.getId(),groupName)
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(scheduledProfile.getId(),groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(scheduledProfile.getPeriod()).repeatForever()
                ).build();

        scheduler.scheduleJob(job, trigger);
    }

    //NOTE : 스케줄러 period 업데이트
    @Override
    public void updateScheduler(String schedulerProfileId, int period) throws SchedulerException {
        ScheduledProfile scheduledProfile = new ScheduledProfile(schedulerProfileId, period);
        schedulerStore.updatePeriod(scheduledProfile);
        logger.debug("ScheduledProfileId = " + schedulerProfileId + " ,  Period = " + period + " Update");

        JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
        scheduler.deleteJob(jobKey);

        JobDetail job = JobBuilder.newJob(ScheduleNotificationManager.class)
                .withIdentity(scheduledProfile.getId(),groupName)
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(scheduledProfile.getId(),groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(scheduledProfile.getPeriod()).repeatForever()
                ).build();

        scheduler.scheduleJob(job, trigger);


//        Trigger oldTrigger = scheduler.getTrigger(TriggerKey.triggerKey(scheduledProfile.getId(), groupName));
//
//        TriggerBuilder triggerBuilder = oldTrigger.getTriggerBuilder();
//
//        Trigger newTrigger = triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//            .withIntervalInSeconds(scheduledProfile.getPeriod()))
//            .build();
//
//        scheduler.rescheduleJob(oldTrigger.getKey() , newTrigger);



//
//        TriggerKey triggerKey = TriggerKey.triggerKey(schedulerProfileId);
//
//        Trigger trigger = TriggerBuilder
//                .newTrigger()
//                .withIdentity(scheduledProfile.getId(),groupName)
//                .withSchedule(
//                        SimpleScheduleBuilder.simpleSchedule()
//                                .withIntervalInSeconds(scheduledProfile.getPeriod()).repeatForever()
//                ).build();
//
//        scheduler.rescheduleJob(triggerKey, trigger);
    }

    //NOTE : 스케줄러 목록 조회
    @Override
    public List<ScheduledProfile> retrieveSchedulerList() {
        List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfile();
        return scheduledProfileList;
    }

    //NOTE : 스케줄러 종료
    @Override
    public void quitScheduler() throws SchedulerException {
        scheduler.shutdown();
    }

    //NOTE : 스케줄러 정지
    @Override
    public void pauseScheduler() throws SchedulerException {
        List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfileByStatus(1);
        for(ScheduledProfile scheduledProfile : scheduledProfileList){
            JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
            scheduler.pauseJob(jobKey);
            logger.debug("PauseScheduledProfile = " + jobKey.getName());
        }
    }

    //NOTE : 스케줄러 재시작
    @Override
    public void restartScheduler() throws SchedulerException {
        List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfileByStatus(1);
        for(ScheduledProfile scheduledProfile : scheduledProfileList){
            JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
            scheduler.resumeJob(jobKey);
            logger.debug("RestartScheduledProfile = " + jobKey.getName());
        }
    }

    //NOTE : 개별 스케줄 정지
    @Override
    public void pauseProfileScheduler(String profileId) throws SchedulerException {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        ScheduledProfile scheduledProfile = new ScheduledProfile(profileId);
        schedulerStore.updateStatus(scheduledProfile, 0);
        JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
        scheduler.pauseJob(jobKey);
        logger.debug("PauseScheduledProfile = " + jobKey.getName());
    }

    //NOTE : 개별 스케줄 재시작
    @Override
    public void restartProfileScheduler(String profileId) throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        ScheduledProfile scheduledProfile = new ScheduledProfile(profileId);
        schedulerStore.updateStatus(scheduledProfile, 1);
        JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
        scheduler.resumeJob(jobKey);
        logger.debug("RestartScheduledProfile = " + jobKey.getName());
    }

    //NOTE : 현재 작동중인 스케줄 목록 조회
    @Override
    public List<ScheduledProfile> retrieveExecuteScheduleList() {
        List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfileByStatus(1);
        logger.debug("ExecuteScheduleProfileList = " + scheduledProfileList.toString());
        return scheduledProfileList;
    }

    //NOTE : 현재 대기중인 스케줄 목록 조회
    @Override
    public List<ScheduledProfile> retrieveReadyScheduleList() {
        List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfileByStatus(0);
        logger.debug("ReadyScheduleProfileList = " + scheduledProfileList.toString());
        return scheduledProfileList;
    }


    //NOTE : SO 실행시 스케줄러 작동 시키는 쓰레드 - 프로퍼니 파일 내용이 false 외에 자동 작동
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            scheduler.start();
            if(!getProperties().equals("false")) {

                List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfileByStatus(1);
                for (ScheduledProfile scheduledProfile : scheduledProfileList) {
                    JobDetail job = JobBuilder.newJob(ScheduleNotificationManager.class)
                            .withIdentity(scheduledProfile.getId(), groupName)
                            .build();

                    Trigger trigger = TriggerBuilder
                            .newTrigger()
                            .withIdentity(scheduledProfile.getId(), groupName)
                            .withSchedule(
                                    SimpleScheduleBuilder.simpleSchedule()
                                            .withIntervalInSeconds(scheduledProfile.getPeriod()).repeatForever()
                            ).build();
                    scheduler.scheduleJob(job, trigger);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
