package com.pineone.icbms.so.profile.store.mongo;

import com.pineone.icbms.so.profile.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 23..
 */
public interface ProfileRepository extends MongoRepository<ProfileDataObject, String> {

    List<ProfileDataObject> findByContextModelId(String contextModelId);
    ProfileDataObject findByName(String profileName);
}
