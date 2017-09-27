package com.pineone.icbms.so.schedule.logic;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.ref.ResponseMessage;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 27..
 */
public interface ISchedulerManager {
    //
	ResponseMessage registerJob(String profileId, int period) throws SchedulerException;
	ResponseMessage quitScheduler() throws SchedulerException;
	ResponseMessage pauseJobList() throws SchedulerException;
	ResponseMessage restartJobList() throws SchedulerException;
	ResponseMessage pauseJob(String profileId) throws SchedulerException;
	ResponseMessage restartJob(String profileId) throws SchedulerException;
    List<ProfileForDB> retrieveExecuteJobList();
    List<ProfileForDB> retrieveReadyJobList();
    ResponseMessage updateJob(String profileId, int period) throws SchedulerException;
    ResponseMessage stopJobListAndChangeStatus();
}
