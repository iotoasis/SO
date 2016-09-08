package com.pineone.icbms.so.scheduler.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by melvin on 2016. 9. 6..
 */
@Document(collection = "scheduler")
public class ScheduledProfileDataObject {
    //

    public ScheduledProfileDataObject() {
    }

    public ScheduledProfileDataObject(String profileId, int period) {
        this.id = profileId;
        Period = period;
    }

    String id;
    int Period;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }
}
