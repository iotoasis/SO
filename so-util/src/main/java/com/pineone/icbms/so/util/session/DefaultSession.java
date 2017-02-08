package com.pineone.icbms.so.util.session;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by existmaster on 2016. 10. 5..
 */
public class DefaultSession implements Session {


    public static final String PROFILE_KEY                      =   "PROFILE_KYE";
    public static final String PROFILE_NAME                     =   "PROFILE_NAME";
    public static final String PRIORITY_KEY                     =   "PRIORITY_KEY";
    public static final String CONTEXTMODEL_KEY                 =   "CONTEXTMODEL_KEY";
    public static final String CONTEXTMODEL_NAME                =   "CONTEXTMODEL_NAME";
    public static final String CONTEXTMODEL_RESULT              =   "CONTEXTMODEL_RESULT";
    public static final String LOCATION_ID                      =   "LOCATION_ID";

    public static final String SERVICEMODEL_KEY                 =   "SERVICEMODEL_KEY";
    public static final String SERVICEMODEL_NAME                =   "SERVICEMODEL_NAME";
    public static final String SERVICEMODEL_RESULT              =   "SERVICEMODEL_RESULT";
    public static final String SERVICE_KEY                      =   "SERVICE_KEY";
    public static final String SERVICE_RESULT                   =   "SERVICE_RESULT";
    public static final String COMPOSITEVIRTUALOBJECT_KEY       =   "COMPOSITEVIRTUALOBJECT_KEY";
    public static final String COMPOSITEVIRTUALOBJECT_RESULT    =   "COMPOSITEVIRTUALOBJECT_RESULT";
    public static final String VIRTUALOBJECT_KEY                =   "VIRTUALOBJECT_KEY";
    public static final String VIRTUALOBJECT_RESULT             =   "VIRTUALOBJECT_RESULT";
    public static final String DEVICE_KEY                       =   "DEVICE_KEY";
    public static final String DEVICE_LOCATION                  =   "DEVICE_LOCATION";
    public static final String DEVICE_RESULT                    =   "DEVICE_RESULT";
    public static final String ADMIN_NOTI_DATA                  =   "ADMIN_NOTI_DATA";

    /**
     * control status
     */
    public static final String CONTROL_EXECUTION                =   "CONTROL_EXECUTION";
    public static final String CONTROL_IGNORE                   =   "CONTROL_IGNORE";
    public static final String CONTROL_ERROR                    =   "CONTROL_ERROR";

    /**
     *  The session unique id.
     */
    private String id;

    /**
     *  The sessionData is pair of key, value.
     */
    private Map<String, String> sessionData;

    private String createDate;

    private Date mongoTime;

    private Long calculateTime;

    public DefaultSession(String id) {
        this.id = id;
    }

    public DefaultSession() {
        id = UUID.randomUUID().toString();
        sessionData = new HashMap<>();
    }

    public DefaultSession(String id, Map<String, String> sessionData) {
        this.id = id;
        this.sessionData = sessionData;
    }

    public DefaultSession(String id, Map<String, String> sessionData, String createDate, Date mongoTime , Long calculateTime) {
        this.id = id;
        this.sessionData = sessionData;
        this.createDate = createDate;
        this.mongoTime = mongoTime;
        this.calculateTime = calculateTime;
    }

    @Override
    public String getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public Long getCalculateTime() {
        return calculateTime;
    }

    @Override
    public void setCalculateTime(Long calculateTime) {
        this.calculateTime = calculateTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getMongoTime() {
        return mongoTime;
    }

    @Override
    public void setMongoTime(Date mongoTime) {
        this.mongoTime = mongoTime;
    }

    @Override
    public boolean isExistSessionData(String key) {
        return sessionData.containsKey(key);
    }
    @Override
    public String findSessionData(String key) {
        return sessionData.get(key);
    }

    @Override
    public String insertSessionData(String key, String value) {
        return sessionData.put(key, value);
    }

    @Override
    public String removeSessionData(String key) {
        return sessionData.remove(key);
    }

    @Override
    public Map<String, String> getSessionData() {
        return this.sessionData;
    }

    @Override
    public String toString() {
        return "DefaultSession{" +
                "id='" + id + '\'' +
                ", sessionData=" + sessionData +
                '}';
    }
}
