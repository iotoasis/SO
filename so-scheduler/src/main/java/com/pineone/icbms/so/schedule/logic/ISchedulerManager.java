package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 27..
 */
public interface ISchedulerManager {
    //
    void registerJob(String profileId, int period) throws SchedulerException;
    void quitScheduler() throws SchedulerException;
    void pauseJobList() throws SchedulerException;
    void restartJobList() throws SchedulerException;
    void pauseJob(String profileId) throws SchedulerException;
    void restartJob(String profileId) throws SchedulerException;
    List<ProfileForDB> retrieveExecuteJobList();
    List<ProfileForDB> retrieveReadyJobList();
    void updateJob(String profileId, int period) throws SchedulerException;
    void stopJobListAndChangeStatus();
}
