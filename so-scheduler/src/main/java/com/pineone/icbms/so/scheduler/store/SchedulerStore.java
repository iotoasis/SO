package com.pineone.icbms.so.scheduler.store;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;

import java.util.List;

/**
 * Created by melvin on 2016. 9. 6..
 */
public interface SchedulerStore {

    void createScheduledProfile(ScheduledProfile scheduledProfile);
    List<ScheduledProfile> retrieveScheduledProfile();
    boolean isExistScheduledProfile(String profileId);
    List<ScheduledProfile> retrieveScheduledProfileByStatus(int status);
    void updateStatus(ScheduledProfile scheduledProfile, int status);
}
