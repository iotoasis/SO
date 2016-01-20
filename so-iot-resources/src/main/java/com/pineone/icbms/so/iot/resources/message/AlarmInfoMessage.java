package com.pineone.icbms.so.iot.resources.message;

/**
 * Created by pahnj on 2016-01-18.
 */
public class AlarmInfoMessage extends DefaultMessage {

    /**
     * AlarmInfo id
     */
    String alarmId;

    /**
     * AlarmInfo action type
     */
    String actionType;

    /**
     * AlarmInfo userid
     */
    String userId;

    /**
     * AlarmInfo wakeTime
     */
    String wakeTime;

    /**
     * AlarmInfo period
     */
    String period;

    public AlarmInfoMessage() {
    }

    public AlarmInfoMessage(String alarmId, String actionType, String userId, String wakeTime, String period) {
        this.alarmId = alarmId;
        this.actionType = actionType;
        this.userId = userId;
        this.wakeTime = wakeTime;
        this.period = period;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
