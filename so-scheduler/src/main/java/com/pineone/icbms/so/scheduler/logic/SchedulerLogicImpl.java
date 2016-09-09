package com.pineone.icbms.so.scheduler.logic;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.proxy.SchedulerProxy;
import com.pineone.icbms.so.scheduler.store.SchedulerStore;
import com.pineone.icbms.so.profile.entity.Profile;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 9. 5..
 */

@Service
public class SchedulerLogicImpl implements SchedulerLogic{

    @Autowired
    SchedulerProxy schedulerProxy;

    @Autowired
    SchedulerStore schedulerStore;

    String groupName = "soGroup";
    Scheduler scheduler = new StdSchedulerFactory().getScheduler();

    public SchedulerLogicImpl() throws SchedulerException {}

    @Override
    public void registerScheduler(String profileId) throws SchedulerException {

        scheduler.start();
        System.out.println("registerScheduler : " + profileId);
        Profile profile = schedulerProxy.retrieveProfile(profileId);
        ScheduledProfile scheduledProfile = new ScheduledProfile(profile.getId(), profile.getPeriod());


//        schedulerStore.createScheduledProfile(scheduledProfile);

        if(schedulerStore.retrieveScheduledProfile().isEmpty()){
            schedulerStore.createScheduledProfile(scheduledProfile);
        }else {
            if(!(schedulerStore.isExistScheduledProfile(scheduledProfile.getId()))){
                schedulerStore.createScheduledProfile(scheduledProfile);
            }
        }

        System.out.println("job : profileId = " + scheduledProfile.getId());
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

        System.out.println("start schedule");
        scheduler.scheduleJob(job, trigger);
        System.out.println(scheduler.isStarted());
    }

    @Override
    public void runScheduler() throws SchedulerException {
        System.out.println(scheduler.isStarted());
        scheduler.start();
        System.out.println(scheduler.isStarted());
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


}
