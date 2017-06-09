package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.interfaces.database.logic.ProfileDAO;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 27..
 */

//Scheduler 기능 구현
@Service
public class SchedulerManager implements ISchedulerManager, Runnable {

    @Autowired
    ProfileDAO profileDAO;

    String groupName = "SoSchedulerGroup";
    Scheduler scheduler;
    private Thread thread;

    // SO 구동시 스케줄러 DB의 내용을 스케줄러에 올려 실행하기 위한 쓰레드 생

    // 스케줄러에 새로운 Job 등록
    @Override
    public void registerJob(String profileId, int period) throws SchedulerException {
        //
        ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);

        JobDetail job = JobBuilder.newJob(SchedulerNotificationManager.class)
                .withIdentity(profileForDB.getId(), groupName)
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(profileForDB.getId(), groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(profileForDB.getPeriod()).repeatForever()
                ).build();

        scheduler.scheduleJob(job, trigger);
        profileForDB.setEnabled(true);
    }

    //스케쥴러 종료
    @Override
    public void quitScheduler() throws SchedulerException {
        scheduler.shutdown();

    }

    //구동중이었던 Job List 정지
    @Override
    public void pauseJobList() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        for(ProfileForDB profileForDB : scheduledProfileList){
            JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
            scheduler.pauseJob(jobKey);
        }
    }

    //정지중이었던 Job List 재시작
    @Override
    public void restartJobList() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        for(ProfileForDB profileForDB : scheduledProfileList){
            JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
            scheduler.resumeJob(jobKey);
        }
    }

    //개별 Job 정지
    @Override
    public void pauseJob(String profileId) throws SchedulerException {
        //
        ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
        scheduler.pauseJob(jobKey);
        profileDAO.updateProfileEnabled(profileId, false);
    }

    //개별 Job 재시작
    @Override
    public void restartJob(String profileId) throws SchedulerException {
        //
        ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
        scheduler.deleteJob(jobKey);
        JobDetail job = JobBuilder.newJob(SchedulerNotificationManager.class)
                .withIdentity(profileForDB.getId(),groupName)
                .build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(profileForDB.getId(),groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(profileForDB.getPeriod()).repeatForever()
                ).build();
        scheduler.scheduleJob(job, trigger);
        profileDAO.updateProfileEnabled(profileId, true);
    }

    // 현재 작동중인 스케쥴 목록 조회
    @Override
    public List<ProfileForDB> retrieveExecuteJobList() {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        return scheduledProfileList;
    }

    // 현재 정지중인 스케쥴 목록 조회
    @Override
    public List<ProfileForDB> retrieveReadyJobList() {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(false);
        return scheduledProfileList;
    }

    // 스케쥴러 period Update
    @Override
    public void updateJob(String profileId, int period) throws SchedulerException {
        //
        ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        profileForDB.setPeriod(period);

        JobDetail job = JobBuilder.newJob(SchedulerNotificationManager.class)
                .withIdentity(profileForDB.getId(),groupName)
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(profileForDB.getId(),groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(profileForDB.getPeriod()).repeatForever()
                ).build();
        scheduler.scheduleJob(job, trigger);
        profileDAO.updateProfilePeriod(profileId, period);
    }

    // 전체 스케줄 정지 (일시 정지 아닌 enabled 값 변환)
    @Override
    public void stopAllJobListAndChangeStatus() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        for(ProfileForDB profileForDB : scheduledProfileList){
            pauseJob(profileForDB.getId());
            profileDAO.updateProfileEnabled(profileForDB.getId(), false);
        }

    }

    @Override
    public void startAllJobListAndChangeStatus() throws SchedulerException {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileList();
        for(ProfileForDB profileForDB : scheduledProfileList){
            restartJob(profileForDB.getId());
            profileDAO.updateProfileEnabled(profileForDB.getId(), true);
        }
    }

    // SO 실행시 자동으로 스케줄러 작동 시키는 쓰레드
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
            scheduler.start();

            List<ProfileForDB> scheduledProfileForDBList = profileDAO.retrieveProfileListByEnable(true);
            for(ProfileForDB profileForDB : scheduledProfileForDBList) {
                JobDetail jobDetail = JobBuilder.newJob(SchedulerNotificationManager.class)
                        .withIdentity(profileForDB.getId(), groupName)
                        .build();

                Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(profileForDB.getId(), groupName)
                        .withSchedule(
                                SimpleScheduleBuilder.simpleSchedule()
                                        .withIntervalInSeconds(profileForDB.getPeriod()).repeatForever())
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (SchedulerException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // SO 구동시 Profile DB의 내용을 스케줄러에 올려 실행하기 위한 쓰레드 생성
    public SchedulerManager() {
        try{
            scheduler = new StdSchedulerFactory().getScheduler();
        }catch(SchedulerException e){
            e.printStackTrace();
        }
        if(thread == null){
            thread = new Thread(this);
        }
        if(!thread.isAlive()){
            thread.start();
        }
    }


}
