package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.ref.ResponseMessage;
import com.pineone.icbms.so.interfaces.database.dao.ProfileDao;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;

/**
 * Created by melvin on 2017. 4. 27..
 */

//Scheduler 기능 구현
@Service
public class SchedulerManager implements ISchedulerManager, Runnable {

    @Autowired
    ProfileDao profileDAO;

    String groupName = "SoSchedulerGroup";
    Scheduler scheduler;
    private Thread thread;

    // SO 구동시 스케줄러 DB의 내용을 스케줄러에 올려 실행하기 위한 쓰레드 생

    // 스케줄러에 새로운 Job 등록
    @Override
    public ResponseMessage registerJob(String profileId, int period) throws SchedulerException {
    	
    	ResponseMessage res = new ResponseMessage();
    	
        ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        if (profileForDB==null || profileForDB.getId().isEmpty()) {
        	res.setMessage("invalid profileId");
        	return res;
        }
        
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
        profileForDB.setEnabled(1);     // true
    	res.setMessage("ok");
    	return res;
    }

    //스케쥴러 종료
    @Override
    public ResponseMessage quitScheduler() throws SchedulerException {
    	ResponseMessage res = new ResponseMessage();
        scheduler.shutdown();
    	res.setMessage("ok");
    	return res;
    }

    //구동중이었던 Job List 정지 ( ProfileDB의 Enabled = 1 인 profile을 읽어서 scheduler 중지시킴)
    @Override
    public ResponseMessage pauseJobList() throws SchedulerException {
    	ResponseMessage res = new ResponseMessage();
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        for(ProfileForDB profileForDB : scheduledProfileList){
            JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
            scheduler.pauseJob(jobKey);
        }
        res.setMessage("Profile size= " + scheduledProfileList.size());
        return res;
    }

    //정지중이었던 Job List 재시작 ( ProfileDB의 Enabled =1 인 profile을 읽어서 scheduler 동작시킴) 
    @Override
    public ResponseMessage restartJobList() throws SchedulerException {
    	ResponseMessage res = new ResponseMessage();
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        for(ProfileForDB profileForDB : scheduledProfileList){
            JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
            scheduler.resumeJob(jobKey);
        }
        
        res.setMessage("Profile size = " + scheduledProfileList.size());
        return res;
    }

    //개별 Job 정지 (profileId에 해당되는 Scheduler 중지시키고 db의 Eabled = 0 로 바꿈)
    @Override
    public ResponseMessage pauseJob(String profileId) throws SchedulerException {
    	ResponseMessage res = new ResponseMessage();

    	ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        if (profileForDB==null || profileForDB.getId().isEmpty()) {
        	res.setMessage("invalid profileId");
        	return res;
        }

        JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
        scheduler.pauseJob(jobKey);

        ProfileForDB schProfileForDB = new ProfileForDB();
        schProfileForDB.setId(profileId);
        schProfileForDB.setEnabled(0);
        profileDAO.updateProfileEnabled(schProfileForDB);
        //profileDAO.updateProfileEnabled(profileId, false);

    	res.setMessage("ok");
        return res;
    }

    //개별 Job 재시작 (profileId에 해당되는 Scheduler 동작 시키고 db의 Eabled =1 로 바꿈)
    @Override
    public ResponseMessage restartJob(String profileId) throws SchedulerException {
    	ResponseMessage res = new ResponseMessage();

    	ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        if (profileForDB==null || profileForDB.getId().isEmpty()) {
        	res.setMessage("invalid profileId");
        	return res;
        }
        
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

        //Enable
        ProfileForDB schProfileForDB = new ProfileForDB();
        schProfileForDB.setId(profileId);
        schProfileForDB.setEnabled(1);
        profileDAO.updateProfileEnabled(schProfileForDB);
        //profileDAO.updateProfileEnabled(profileId, true);

        res.setMessage("ok");
        return res;
    }

    // 현재 작동중인 스케쥴 목록 조회 (Enabled = 1)
    @Override
    public List<ProfileForDB> retrieveExecuteJobList() {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        return scheduledProfileList;
    }

    // 현재 정지중인 스케쥴 목록 조회 (Enabled = 0)
    @Override
    public List<ProfileForDB> retrieveReadyJobList() {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(false);
        return scheduledProfileList;
    }

    // 스케쥴러 period Update
    @Override
    public ResponseMessage updateJob(String profileId, int period) throws SchedulerException {
    	ResponseMessage res = new ResponseMessage();

    	ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        if (profileForDB==null || profileForDB.getId().isEmpty()) {
        	res.setMessage("invalid profileId");
        	return res;
        }
    	
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

        ProfileForDB schProfileForDB = new ProfileForDB();
        schProfileForDB.setId(profileId);
        schProfileForDB.setPeriod(period);
        profileDAO.updateProfilePeriod(schProfileForDB);
        
        res.setMessage("ok");
        return res;
    }

    // 전체 스케줄 정지 (일시 정지 아닌 enabled 값 변환)
    @Override
    public ResponseMessage stopJobListAndChangeStatus() {
    	ResponseMessage res = new ResponseMessage();

    	ProfileForDB schProfileForDB = new ProfileForDB();
        schProfileForDB.setEnabled(0);
        profileDAO.updateProfileEnabledAll(schProfileForDB);

        res.setMessage("ok");
        return res;
    }

    // SO 실행시 자동으로 스케줄러 작동 시키는 쓰레드
    @Override
    public void run() {
        try{
            Thread.sleep(10000); //for preparing Jetty
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
    
    @PostConstruct
    public void SchedulerManager1() {
  	//public SchedulerManager() {    	
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
