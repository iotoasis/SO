package com.pineone.icbms.so.virtualobject.entity;

public class VirtualObject {
    //

    /**
     * Device functionality definition
     */
    public static final String FUNCTIONLITY_TEMP_CONTROL                 =   "temp-control";
    public static final String FUNCTIONLITY_POWER_CONTROL                =   "power-control";
    public static final String FUNCTIONLITY_OPEN_CONTROL                 =   "open-control";
    public static final String FUNCTIONLITY_ADMIN_NOTI                   =   "http://www.iotoasis.org/ontology/admin-aspect";


    /**
     * VirtualObject 식별자
     * format : vo-(devicename)-(service)
     * ex) vo-smartlight01-001-power-control
     */
    private String id;

    /**
     * VirtualObject 이름
     * format :free
     * ex) SmartLight01-001 VO
     */
    private String voName;

    /**
     * VirtualObject 기능 서비스
     * format : icbms-voservice-(devicename)-(service)
     * ex) icbms-voservice-ventilator-power-control
     */
    private String functionality;

    /**
     * VirtualObject 설명
     * format : free
     * ex) 강의실 스마조명 제어 서비스
     */
    private String voDescription;

    /**
     * VirtualObject 생성 시간
     * format : yyyymmddhhmm
     * ex) 201608250930
     */
    private String voCreateTime;

    /**
     * VirtualObject의 만기 시간
     * format : yyyymmddhhmm
     * ex) 201708250930
     */
    private String voExpiredTime;

    /**
     * VirtualObject에 연결된 Device의 aspect
     * format : Device aspect
     * ex) power-control
     */
    private String aspect;

    /**
     * VirtualObject에 연결된 Device의 식별자
     * format : CSE relative uri
     * ex) /herit-in/herit-cse/ONDB_SmartLight01_001
     */
    private String deviceId;

    /**
     * VirtualObject에서 제어할 Device의 명령어
     * format : device command
     * ex) ON
     */
    private String voCommand;

    /**
     * VirtualObject가 제공되는 서비스의 위치
     * format : free
     * ex) T1ENG_605
     */
    private String voLocation;


    public VirtualObject() {
    }

    public VirtualObject(String id, String voName, String functionality, String voDescription, String voCreateTime, String voExpiredTime, String aspect, String deviceId, String voCommand, String voLocation) {
        this.id = id;
        this.voName = voName;
        this.functionality = functionality;
        this.voDescription = voDescription;
        this.voCreateTime = voCreateTime;
        this.voExpiredTime = voExpiredTime;
        this.aspect = aspect;
        this.deviceId = deviceId;
        this.voCommand = voCommand;
        this.voLocation = voLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getVoDescription() {
        return voDescription;
    }

    public void setVoDescription(String voDescription) {
        this.voDescription = voDescription;
    }

    public String getVoCreateTime() {
        return voCreateTime;
    }

    public void setVoCreateTime(String voCreateTime) {
        this.voCreateTime = voCreateTime;
    }

    public String getVoExpiredTime() {
        return voExpiredTime;
    }

    public void setVoExpiredTime(String voExpiredTime) {
        this.voExpiredTime = voExpiredTime;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVoCommand() {
        return voCommand;
    }

    public void setVoCommand(String voCommand) {
        this.voCommand = voCommand;
    }

    public String getVoLocation() {
        return voLocation;
    }

    public void setVoLocation(String voLocation) {
        this.voLocation = voLocation;
    }

    @Override
    public String toString() {
        return "VirtualObject{" +
                "id='" + id + '\'' +
                ", voName='" + voName + '\'' +
                ", functionality='" + functionality + '\'' +
                ", voDescription='" + voDescription + '\'' +
                ", voCreateTime='" + voCreateTime + '\'' +
                ", voExpiredTime='" + voExpiredTime + '\'' +
                ", aspect='" + aspect + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", voCommand='" + voCommand + '\'' +
                ", voLocation='" + voLocation + '\'' +
                '}';
    }
}

