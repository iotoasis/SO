package com.pineone.icbms.so.scheduler.entity;

/**
 * Created by melvin on 2016. 9. 6..
 */
//NOTE : 프로파일에서 스케줄관련 내용만 추출
public class ScheduledProfile {

    String id;
    int period;
    int status;

    public ScheduledProfile() {
    }

    public ScheduledProfile(String id, int period) {
        this.id = id;
        this.period = period;
    }

    public ScheduledProfile(String profileId) {
        this.id = profileId;
    }

    public ScheduledProfile(String id, int period, int status) {
        this.id = id;
        this.period = period;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScheduledProfile{" +
                "id='" + id + '\'' +
                ", period=" + period +
                ", status=" + status +
                '}';
    }
}
