package com.pineone.icbms.so.scheduler.logic;

import org.quartz.SchedulerException;

/**
 * Created by melvin on 2016. 9. 5..
 */
public interface SchedulerLogic {
    void registerScheduler(String profileId) throws SchedulerException;
    void quitScheduler() throws SchedulerException;
    void pauseScheduler() throws SchedulerException;
    void restartScheduler() throws SchedulerException;
    void pauseProfileScheduler(String profileId) throws SchedulerException;
    void restartProfileScheduler(String profileId) throws SchedulerException;
}
