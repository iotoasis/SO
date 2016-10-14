package com.pineone.icbms.so.util.session.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * Created by melvin on 2016. 10. 7..
 */
//NOTE : 디비에 저장하기 위한 형태의 session Data
@Document(collection = "session")
public class SessionDataObject {

    private String id;

    private String createDate;

    private Map<String, String> sessionData;

    private Date mongoTime;

    private Long calculateTime;

    public SessionDataObject() {
    }


    public SessionDataObject(String id) {
        this.id = id;
    }

    public SessionDataObject(String id, Map<String, String> sessionData) {
        this.id = id;
        this.sessionData = sessionData;
    }

    public SessionDataObject(String id, Map<String, String> sessionData, String createDate, Date mongoTime, Long calculateTime) {
        this.id = id;
        this.sessionData = sessionData;
        this.createDate = createDate;
        this.mongoTime = mongoTime;
        this.calculateTime = calculateTime;
    }

    public Date getMongoTime() {
        return mongoTime;
    }

    public void setMongoTime(Date mongoTime) {
        this.mongoTime = mongoTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getCalculateTime() {
        return calculateTime;
    }

    public void setCalculateTime(Long calculateTime) {
        this.calculateTime = calculateTime;
    }

    public Map<String, String> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Map<String, String> sessionData) {
        this.sessionData = sessionData;
    }

    @Override
    public String toString() {
        return "SessionDataObject{" +
                "id='" + id + '\'' +
                ", sessionData=" + sessionData +
                '}';
    }
}
