package com.pineone.icbms.so.scheduler.entity;

/**
 * Created by melvin on 2016. 9. 6..
 */
//NOTE : 프로파일에서 스케줄관련 내용만 추출
public class ScheduledProfile {

    String id;
    int Period;

    public ScheduledProfile() {
    }

    public ScheduledProfile(String id, int period) {
        this.id = id;
        Period = period;
    }

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

    @Override
    public String toString() {
        return "ScheduledProfile{" +
                "id='" + id + '\'' +
                ", Period=" + Period +
                '}';
    }
}
