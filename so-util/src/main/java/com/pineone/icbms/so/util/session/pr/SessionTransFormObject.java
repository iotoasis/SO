package com.pineone.icbms.so.util.session.pr;

import java.util.Map;

public class SessionTransFormObject {
    //

    private String id;

    private String createDate;

    private Map<String, String> sessionData;

    public SessionTransFormObject() {
    }

    public SessionTransFormObject(String id, String createDate, Map<String, String> sessionData) {
        this.id = id;
        this.createDate = createDate;
        this.sessionData = sessionData;
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

    public Map<String, String> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Map<String, String> sessionData) {
        this.sessionData = sessionData;
    }

    @Override
    public String toString() {
        return "SessionTransFormObject{" +
                "id='" + id + '\'' +
                ", createDate='" + createDate + '\'' +
                ", sessionData=" + sessionData +
                '}';
    }
}
