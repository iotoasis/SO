package com.pineone.icbms.so.scheduler.proxy;

import com.pineone.icbms.so.profile.entity.Profile;

/**
 * Created by melvin on 2016. 9. 5..
 */
public interface SchedulerProxy {
    Profile retrieveProfile(String profileId);
    void executeScheduledProfile(String profileId);
}
