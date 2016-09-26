package com.pineone.icbms.so.scheduler.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by melvin on 2016. 9. 6..
 */
//NOTE : 디비에 저장하기 위한 형태의 scheduleProfile
@Document(collection = "scheduler")
public class ScheduledProfileDataObject {
    //

    public ScheduledProfileDataObject() {
    }

    public ScheduledProfileDataObject(String id, int period, int status) {
        this.id = id;
        this.period = period;
        this.status = status;
    }

    public ScheduledProfileDataObject(String id, int period) {
        this.id = id;
        this.period = period;
    }

    public ScheduledProfileDataObject(String id) {
        this.id = id;
    }

    String id;
    int period;
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ScheduledProfileDataObject{" +
                "id='" + id + '\'' +
                ", period=" + period +
                ", status=" + status +
                '}';
    }
}
