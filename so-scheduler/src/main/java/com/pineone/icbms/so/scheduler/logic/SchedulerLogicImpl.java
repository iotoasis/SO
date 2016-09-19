package com.pineone.icbms.so.scheduler.logic;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.proxy.SchedulerProxy;
import com.pineone.icbms.so.scheduler.store.SchedulerStore;
import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.InputStreamEditor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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

    @Override
    public void registerScheduler(String profileId) throws SchedulerException {


        logger.debug("ProfileId = " + profileId);
        Profile profile = schedulerProxy.retrieveProfile(profileId);
        ScheduledProfile scheduledProfile = new ScheduledProfile(profile.getId(), profile.getPeriod());
        logger.debug("ScheduledProfile = " + scheduledProfile.toString());

//        schedulerStore.createScheduledProfile(scheduledProfile);

        if(schedulerStore.retrieveScheduledProfile().isEmpty()){
            schedulerStore.createScheduledProfile(scheduledProfile);
        }else {
            if(!(schedulerStore.isExistScheduledProfile(scheduledProfile.getId()))){
                schedulerStore.createScheduledProfile(scheduledProfile);
            }
        }

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

    @Override
    public void quitScheduler() throws SchedulerException {
        scheduler.shutdown();
    }

    @Override
    public void pauseScheduler() throws SchedulerException {
        scheduler.pauseAll();
    }

    @Override
    public void restartScheduler() throws SchedulerException {
        scheduler.resumeAll();
    }

    @Override
    public void pauseProfileScheduler(String profileId) throws SchedulerException {
        //
        logger.info(LogPrint.outputInfoLogPrint());
        Profile profile = schedulerProxy.retrieveProfile(profileId);
        ScheduledProfile scheduledProfile = new ScheduledProfile(profile.getId(), profile.getPeriod());
        JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
        scheduler.pauseJob(jobKey);
        logger.debug("PauseScheduledProfile = " + jobKey.getName());
    }

    @Override
    public void restartProfileScheduler(String profileId) throws SchedulerException {
        logger.info(LogPrint.outputInfoLogPrint());
        Profile profile = schedulerProxy.retrieveProfile(profileId);
        ScheduledProfile scheduledProfile = new ScheduledProfile(profile.getId(), profile.getPeriod());
        JobKey jobKey = JobKey.jobKey(scheduledProfile.getId(), groupName);
        scheduler.resumeJob(jobKey);
        logger.debug("RestartScheduledProfile = " + jobKey.getName());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);

            if(!getProperties().equals("false")) {
                scheduler.start();
                List<ScheduledProfile> scheduledProfileList = schedulerStore.retrieveScheduledProfile();
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
