package com.pineone.icbms.so.scheduler.store.mongo;

import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import com.pineone.icbms.so.scheduler.store.SchedulerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 9. 6..
 */

@Repository
public class SchedulerStoreMongoImpl implements SchedulerStore {

    @Autowired
    SchedulerRepository schedulerRepository;


    @Override
    public void createScheduledProfile(ScheduledProfile scheduledProfile) {
        ScheduledProfileDataObject scheduledProfileDataObject = scheduledProfileToDataObject(scheduledProfile);
        schedulerRepository.save(scheduledProfileDataObject);
    }

    @Override
    public List<ScheduledProfile> retrieveScheduledProfile() {
        List<ScheduledProfileDataObject> scheduledProfileDataObjectList = schedulerRepository.findAll();
        List<ScheduledProfile> scheduledProfileList = new ArrayList<>();
        for(ScheduledProfileDataObject scheduledProfileDataObject : scheduledProfileDataObjectList){
            scheduledProfileList.add(dataObjectToSchedulerDataObject(scheduledProfileDataObject));
        }
        return scheduledProfileList;
    }

    @Override
    public boolean isExistScheduledProfile(String profileId) {
        boolean checker = schedulerRepository.exists(profileId);
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
