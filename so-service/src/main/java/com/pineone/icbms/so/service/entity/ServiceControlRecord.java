package com.pineone.icbms.so.service.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Service를 제어시 필터를 위한 기록 클래스
 */
@Document(collection = "servicecontrolrecord")
public class ServiceControlRecord {
    //
    /**
     * Service 제어 데이터의 식별자
     */
    private String      id;

    /**
     * Service 제어 데이터의 샐성 시간
     */
    private long        createTime;

    /**
     * Service 제어 데이터의 수정 시간
     */
    private long      modifiedTime;

    /**
     * Service 제어 데이터의 제어 결과
     * actived or ignore
     */
    private String      result;

    /**
     * Service 제어 데이터의 주기
     */
    private long      period;

    public static final long DEFAULTTIME = 5000;
    public static final String SERIVCE_ACTIVED  = "actived";
    public static final String SERIVCE_IGNORE   = "ignore";

    public ServiceControlRecord() {
    }

    public ServiceControlRecord(String id, long createTime, long modifiedTime, String result, long period) {
        this.id = id;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.result = result;
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public boolean checkActivedService(long currentTime){
        if((this.modifiedTime + this.period) < currentTime){
            this.period = DEFAULTTIME;
            return true;
        } else {
            this.period = this.period - (currentTime - this.modifiedTime);
            return false;
        }
    }


    @Override
    public String toString() {
        return "ServiceControlRecordDataObject{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", result='" + result + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
