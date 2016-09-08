package com.pineone.icbms.so.scheduler.proxy;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.pr.ProfilePresentation;
import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 9. 6..
 */

@Service
public class SchedulerInnerProxy implements SchedulerProxy {

    @Autowired
    ProfilePresentation profilePresentation;

    @Override
    public Profile retrieveProfile(String profileId) {
        //
        Profile profile = profilePresentation.retrieveProfileDetailController(profileId);
        return profile;
    }

    @Override
    public void executeScheduledProfile(String profileId) {
        //
        System.out.println("여기는넘어오니");
//        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileId);
//        profilePresentation.executeScheduleProfile(profileTransFormData);
    }
}
