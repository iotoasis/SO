package com.pineone.icbms.so.scheduler.store.mongo;

import com.pineone.icbms.so.profile.store.mongo.ProfileDataObject;
import com.pineone.icbms.so.scheduler.entity.ScheduledProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by melvin on 2016. 9. 6..
 */
public interface SchedulerRepository extends MongoRepository<ScheduledProfileDataObject, String>  {

    List<ScheduledProfileDataObject> findByStatus(int status);
    ScheduledProfileDataObject findById(String id);
}
