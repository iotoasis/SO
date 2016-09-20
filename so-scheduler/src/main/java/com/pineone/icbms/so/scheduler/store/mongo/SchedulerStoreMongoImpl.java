package com.pineone.icbms.so.scheduler.store.mongo;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.store.SchedulerStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 9. 6..
 */

@Repository
public class SchedulerStoreMongoImpl implements SchedulerStore {

    public static final Logger logger = LoggerFactory.getLogger(SchedulerStoreMongoImpl.class);

    @Autowired
    SchedulerRepository schedulerRepository;


    @Override
    public void createScheduledProfile(ScheduledProfile scheduledProfile) {
        logger.debug("ScheduledProfile = " + scheduledProfile.toString());
        ScheduledProfileDataObject scheduledProfileDataObject = scheduledProfileToDataObject(scheduledProfile);
        schedulerRepository.save(scheduledProfileDataObject);
    }

    @Override
    public List<ScheduledProfile> retrieveScheduledProfile() {
        List<ScheduledProfileDataObject> scheduledProfileDataObjectList = schedulerRepository.findAll();
        List<ScheduledProfile> scheduledProfileList = new ArrayList<>();
        for(ScheduledProfileDataObject scheduledProfileDataObject : scheduledProfileDataObjectList){
            scheduledProfileList.add(dataObjectToSchedulerDataObject(scheduledProfileDataObject));
            logger.debug("ScheduledProfile = " + dataObjectToSchedulerDataObject(scheduledProfileDataObject));
        }
        return scheduledProfileList;
    }

    //NOTE : 디비에 해당 프로파일 내용이 있는지 확인
    @Override
    public boolean isExistScheduledProfile(String profileId) {
        boolean checker = schedulerRepository.exists(profileId);
        logger.debug("ProfileId = " + profileId);
        return checker;
    }

    private ScheduledProfileDataObject scheduledProfileToDataObject(ScheduledProfile scheduledProfile) {
        if(scheduledProfile == null) return null;
        return new ScheduledProfileDataObject(scheduledProfile.getId(), scheduledProfile.getPeriod());
    }

    private ScheduledProfile dataObjectToSchedulerDataObject(ScheduledProfileDataObject schedulerDataObject){
        if(schedulerDataObject == null) return null;
        return new ScheduledProfile(schedulerDataObject.getId(), schedulerDataObject.getPeriod());
    }
}
