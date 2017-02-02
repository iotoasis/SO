package com.pineone.icbms.so.scheduler.logic;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by melvin on 2016. 9. 5..
 */
public interface SchedulerLogic {
    void registerScheduler(String profileId, int period) throws SchedulerException;
    void quitScheduler() throws SchedulerException;
    void pauseScheduler() throws SchedulerException;
    void restartScheduler() throws SchedulerException;
    void pauseProfileScheduler(String profileId) throws SchedulerException;
    void restartProfileScheduler(String profileId) throws SchedulerException;
    List<ScheduledProfile> retrieveExecuteScheduleList();
    List<ScheduledProfile> retrieveReadyScheduleList();
    void updateScheduler(String id, int period) throws SchedulerException;
    List<ScheduledProfile> retrieveSchedulerList();
    void stopScheduler();
    void startScheduler();
    void createSchedulerData(String id, int period);
}
