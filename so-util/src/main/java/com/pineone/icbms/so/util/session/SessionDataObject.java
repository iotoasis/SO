package com.pineone.icbms.so.util.session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by melvin on 2016. 10. 7..
 */
//NOTE : 디비에 저장하기 위한 형태의 session Data
//@Document(collection = "session")
@ToString
public class SessionDataObject {

    @Getter @Setter
    private String id;
    @Getter @Setter
    private String createDate;
    @Getter @Setter
    private SessionData sessionData;
    @Getter @Setter
    private String mongoTime;
    @Getter @Setter
    private Long calculateTime;

    public SessionDataObject() {
    }

    public SessionDataObject(String id) {
        this.id = id;
    }

    public SessionDataObject(String id, SessionData sessionData) {
        this.id = id;
        this.sessionData = sessionData;
    }

    public SessionDataObject(String id, SessionData sessionData, String createDate, String mongoTime, Long calculateTime) {
        this.id = id;
        this.sessionData = sessionData;
        this.createDate = createDate;
        this.mongoTime = mongoTime;
        this.calculateTime = calculateTime;
    }

//    public String getMongoTime() {
//        return mongoTime;
//    }
//
//    public void setMongoTime(String mongoTime) {
//        this.mongoTime = mongoTime;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
//
//    public Long getCalculateTime() {
//        return calculateTime;
//    }
//
//    public void setCalculateTime(Long calculateTime) {
//        this.calculateTime = calculateTime;
//    }
//
//    public SessionData getSessionData() {
//        return sessionData;
//    }
//
//    public void setSessionData(SessionData sessionData) {
//        this.sessionData = sessionData;
//    }

//    @Override
//    public String toString() {
//        return "SessionDataObject{" +
//                "id='" + id + '\'' +
//                ", sessionData=" + sessionData +
//                '}';
//    }
}
