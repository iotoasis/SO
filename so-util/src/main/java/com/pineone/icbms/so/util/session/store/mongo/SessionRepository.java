package com.pineone.icbms.so.util.session.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by melvin on 2016. 10. 7..
 */
public interface SessionRepository extends MongoRepository<SessionDataObject, String>{
}
