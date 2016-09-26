package com.pineone.icbms.so.scheduler.proxy;

import com.pineone.icbms.so.profile.entity.Profile;
import com.pineone.icbms.so.profile.pr.ProfilePresentation;
import com.pineone.icbms.so.scheduler.pr.SchedulerPresentation;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 9. 6..
 */

@Service
public class SchedulerInnerProxy implements SchedulerProxy {

    public static final Logger logger = LoggerFactory.getLogger(SchedulerPresentation.class);

    @Autowired
    ProfilePresentation profilePresentation;

    @Override
    public Profile retrieveProfile(String profileId) {
        //
        logger.info(LogPrint.outputInfoLogPrint() + ", ProfileId = " + profileId);
        logger.debug("ProfileId = " + profileId);
        Profile profile = profilePresentation.retrieveProfileDetailController(profileId);
        return profile;
    }

//    @Override
//    public void executeScheduledProfile(String profileId) {
//        //
//        logger.info(LogPrint.outputInfoLogPrint() + ", ProfileId = " + profileId);
//        logger.debug("ProfileId = " + profileId);
//        System.out.println("여기는넘어오니");
////        ProfileTransFormData profileTransFormData = new ProfileTransFormData(profileId);
//        profilePresentation.executeScheduleProfile(profileTransFormData);
//    }
}
