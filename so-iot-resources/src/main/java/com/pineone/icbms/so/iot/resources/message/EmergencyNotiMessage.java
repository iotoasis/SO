package com.pineone.icbms.so.iot.resources.message;

/**
 * Created by pahnj on 2016-01-18.
 */
public class EmergencyNotiMessage extends DefaultMessage {

    /**
     * EmergencyNoti zone
     */
    String zone;

    /**
     * EmergencyNoti kind
     */
    String kind;

    /**
     * EmergencyNoti userid
     */
    String userId;

    /**
     * EmergencyNoti camurl
     */
    String camUrl;

    public EmergencyNotiMessage() {
    }

    public EmergencyNotiMessage(String zone, String kind, String userId, String camUrl) {
        this.zone = zone;
        this.kind = kind;
        this.userId = userId;
        this.camUrl = camUrl;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCamUrl() {
        return camUrl;
    }

    public void setCamUrl(String camUrl) {
        this.camUrl = camUrl;
    }
}
