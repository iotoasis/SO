package com.pineone.icbms.so.iot.resources.message;

/**
 * Created by pahnj on 2016-01-18.
 */
public class AlarmInfoMessage {

    /**
     * AlarmInfo id
     */
    String alarm_id;

    /**
     * AlarmInfo action type
     */
    String actionType;

    /**
     * AlarmInfo userid
     */
    String user_id;

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

    public AlarmInfoMessage(String alarm_id, String actionType, String user_id, String wakeTime, String period) {
        this.alarm_id = alarm_id;
        this.actionType = actionType;
        this.user_id = user_id;
        this.wakeTime = wakeTime;
        this.period = period;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
