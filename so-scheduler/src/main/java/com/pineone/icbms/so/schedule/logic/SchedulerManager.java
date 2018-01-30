package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.ref.ResponseMessage;

import com.pineone.icbms.so.interfaces.database.dao.ProfileDao;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;

/**
 * Created by melvin on 2017. 4. 27..
 */

//Scheduler 기능 구현
@Service
public class SchedulerManager implements ISchedulerManager, Runnable {

	protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProfileDao profileDAO;

    String groupName = "SoSchedulerGroup";
    Scheduler scheduler;
    private Thread thread;

    // SO 구동시 스케줄러 DB의 내용을 스케줄러에 올려 실행하기 위한 쓰레드 생성

    // 스케줄러에 새로운 Job 등록
    @Override
    public ResponseMessage registerJob(String profileId, int period) throws SchedulerException {
    	
    	ResponseMessage res = new ResponseMessage();
    	
        ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        if (profileForDB==null || profileForDB.getId().isEmpty()) {
        	res.setMessage("invalid profileId");
        	return res;
        }
        profileForDB.setPeriod(period);
    	
        startSchedule(profileForDB);

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

    //구동중이었던 Job List 정지 ( 동작중인 scheduler중지시킴)
    @Override
    public ResponseMessage pauseJobList() throws SchedulerException {

    	ResponseMessage res = new ResponseMessage();
		//Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		int count =0;
		log.debug(" ==============< executedJobList() > ======================");
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				String jobGroup = jobKey.getGroup();
		
				//get job's trigger
				@SuppressWarnings("unchecked")
				List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				Trigger trigger = triggers.get(0); 
				Date nextFireTime = trigger.getNextFireTime();
				
				//[jobName] : PR-8ea4d232-6e0d-42cc-a7b1-7f1a1c03951c [groupName] : SoSchedulerGroup - Fri Sep 29 14:29:31 KST 2017
		
				TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				if (triggerState.equals(TriggerState.NORMAL)) {
					//jobName으로 DB에서 Profile 정보 읽어오기
					ProfileForDB profileForDB = profileDAO.retrieveProfile(jobName);
					if (profileForDB != null) {
						//profileList.add(profileForDB);
						scheduler.pauseJob(jobKey);
						count ++;
						log.debug(" === [jobName] : " + jobName
								+ " [groupName] : "  + jobGroup 
								+ " - " + nextFireTime
								+ " = triggerState : " + triggerState.toString());
					}
				} 
			}
		}
        res.setMessage("Paused profile count= " + count);
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
        
        res.setMessage("Executing profile count = " + scheduledProfileList.size());
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

    	startSchedule(profileForDB);

        //Enable
        ProfileForDB schProfileForDB = new ProfileForDB();
        schProfileForDB.setId(profileId);
        schProfileForDB.setEnabled(1);
        profileDAO.updateProfileEnabled(schProfileForDB);
        //profileDAO.updateProfileEnabled(profileId, true);

        res.setMessage("Started and Enabled");
        return res;
    }

    // 현재 작동중인 스케쥴 목록 조회 
    @Override
    public List<ProfileForDB> executedJobList() {

    	List<ProfileForDB> profileList = new ArrayList<ProfileForDB>();
    	
    	try {
    		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

			log.debug(" ==============< executedJobList() > ======================");
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					//get job's trigger
					@SuppressWarnings("unchecked")
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Trigger trigger = triggers.get(0); 
					Date nextFireTime = trigger.getNextFireTime();
					
					//[jobName] : PR-8ea4d232-6e0d-42cc-a7b1-7f1a1c03951c [groupName] : SoSchedulerGroup - Fri Sep 29 14:29:31 KST 2017

					TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					if (triggerState.equals(TriggerState.NORMAL)) {
						//jobName으로 DB에서 Profile 정보 읽어오기
						ProfileForDB profileForDB = profileDAO.retrieveProfile(jobName);
						if (profileForDB != null) {
							profileList.add(profileForDB);
						}
					} 
					log.debug(" === [jobName] : " + jobName
								+ " [groupName] : "  + jobGroup 
								+ " - " + nextFireTime
								+ " = triggerState : " + triggerState.toString());
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    	   
        return profileList;
    }

    // Enabled Profile 목록 조회 (Enabled = 1)
    @Override
    public List<ProfileForDB> retrieveEnabledJobList() {
        //
        List<ProfileForDB> scheduledProfileList = profileDAO.retrieveProfileListByEnable(true);
        return scheduledProfileList;
    }

    // Disabled Profile 목록 조회 (Enabled = 0)
    @Override
    public List<ProfileForDB> retrieveDisabledJobList() {
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

    	startSchedule(profileForDB);
    	
        ProfileForDB schProfileForDB = new ProfileForDB();
        schProfileForDB.setId(profileId);
        schProfileForDB.setPeriod(period);
        profileDAO.updateProfilePeriod(schProfileForDB);
        
        res.setMessage("ok");
        return res;
    }

    // 전체 Profile DB disable
    @Override
    public ResponseMessage disableAllProfiles() {
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
            //DB에서 Enabled된 Profile 목록 읽어옴
            List<ProfileForDB> scheduledProfileForDBList = profileDAO.retrieveProfileListByEnable(true);
            for(ProfileForDB profileForDB : scheduledProfileForDBList) {
            	startSchedule(profileForDB);
            }
            Thread.sleep(10000); //for preparing Jetty
            log.debug("scheduler started");
            scheduler.start();
        } catch (SchedulerException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

	private void startSchedule(ProfileForDB profileForDB) throws SchedulerException {
		String profileId = profileForDB.getId();
		Integer period = profileForDB.getPeriod();
		Integer checkRate = profileForDB.getCheckRate();
		
		if (period==null)
			period = 24*60*60; //1일
		if (checkRate==null)
			checkRate = 30;  //30초
		
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("profile",profileForDB);
		//dataMap.put("checkRate", checkRate);
		dataMap.put("happened", false); //초기값=미발생
		dataMap.put("lastTimeExecutedCm", 0L); //마지막 CM 발생 일시

		JobDetail job = JobBuilder.newJob(SchedulerNotificationManager.class)
		        .withIdentity(profileId, groupName)
		        .usingJobData(dataMap)
//                        .usingJobData("period", period)
//                        .usingJobData("checkRate", checkRate)
//                        .usingJobData("happened", false) //초기값=미발생
//                        .usingJobData("lastTimeExecutedCm", 0) //마지막 CM 발생 일시
		        .build();

		Trigger trigger = TriggerBuilder
		        .newTrigger()
		        .withIdentity(profileId, groupName)
		        .withSchedule(
		                SimpleScheduleBuilder.simpleSchedule()
		                .withIntervalInSeconds(checkRate).repeatForever()
		        ).build();

		//스케쥴러에 추가
		scheduler.scheduleJob(job, trigger);
		log.debug("Added job : {}:{}", profileForDB.getName(), job.getKey());

/*        
        // 스케쥴러 Period를 바꾸는 방법
        int newPeriod = period;
        Trigger newTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity(profileId,groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(newPeriod).repeatForever()
                ).build();

        
        Trigger oldTrigger = context.getTrigger();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        
        scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
*/
	
	}

	// SO 구동시 Profile DB의 내용을 스케줄러에 올려 실행하기 위한 쓰레드 생성
    @PostConstruct
    public void schedulerManagerInit() {
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

	// profileId를 받아 해당 profileID에 해당하는 스케쥴러를 disable 시킴
    @Override
    public ResponseMessage disableSchedule(String profileId) throws SchedulerException {
    	
    	ResponseMessage res = new ResponseMessage();

    	ProfileForDB profileForDB = profileDAO.retrieveProfile(profileId);
        if (profileForDB==null || profileForDB.getId().isEmpty()) {
        	res.setMessage("invalid profileId");
        	return res;
        }

        JobKey jobKey = JobKey.jobKey(profileForDB.getId(), groupName);
        scheduler.pauseJob(jobKey);
        res.setMessage("ok");
    	
    	
    	return res;
    }
}
