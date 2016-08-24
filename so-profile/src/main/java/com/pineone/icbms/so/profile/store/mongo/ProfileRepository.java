package com.pineone.icbms.so.profile.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by melvin on 2016. 8. 23..
 */
public interface ProfileRepository extends MongoRepository<ProfileDataObject, String> {
}
