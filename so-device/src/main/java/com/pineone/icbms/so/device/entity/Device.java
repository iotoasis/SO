package com.pineone.icbms.so.device.entity;

import java.util.List;

public class Device {

    // Command Device Parameter

    /**
     * Device의 식별자
     * format : CSE relative uri
     * ex) /herit-in/herit-cse/ONDB_BeamProjector01_001
     */
    private String          deviceId;

    /**
     * Device의 이름
     * format : DeviceID
     * ex) ONDB_BeamProjector01_001
     */
    private String          deviceName;

    /**
     * Device의 설치 위치
     * format : free
     * ex) T1ENG_605_HUB01
     */
    private String          deviceLocation;

    /**
     * Device의 제어 가능한 Uri
     * format : CSE relative uri
     * ex) /herit-in/herit-cse/ONDB_BeamProjector01_001
     */
    private String          deviceUri;

    /**
     * Device의 명령어 List
     * format : Device Command
     * ex) ON,OFF
     */
    private List<String>    deviceCommand;

    /**
     * Device의 서비스 List
     * format : Device Service
     * ex) BeamProjector_PowerControl_ON, BeamProjector_PowerControl_OFF
     */
    private List<String>    deviceServices;

    /**
     * Device의 생성 시간
     * format : yyyymmddhhmm
     * ex) 201608250930
     */
    private long          deviceCreateTime;

    /**
     * Device의 생성 만기 시간
     * format : yyyymmddhhmm
     * ex) 201708250930
     */
    private long          deviceExfiredTime;

    /**
     * Device의 상태
     * format : Device Status
     * ex) ON
     */
    private String deviceStatus;

    public Device() {
    }

    public Device(String deviceId, String deviceName, String deviceLocation, String deviceUri, List<String> deviceCommand, List<String> deviceServices, long deviceCreateTime, long deviceExfiredTime, String deviceStatus) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceCommand = deviceCommand;
        this.deviceServices = deviceServices;
        this.deviceCreateTime = deviceCreateTime;
        this.deviceExfiredTime = deviceExfiredTime;
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public List<String> getDeviceCommand() {
        return deviceCommand;
    }

    public void setDeviceCommand(List<String> deviceCommand) {
        this.deviceCommand = deviceCommand;
    }

    public List<String> getDeviceServices() {
        return deviceServices;
    }

    public void setDeviceServices(List<String> deviceServices) {
        this.deviceServices = deviceServices;
    }

    public long getDeviceCreateTime() {
        return deviceCreateTime;
    }

    public void setDeviceCreateTime(long deviceCreateTime) {
        this.deviceCreateTime = deviceCreateTime;
    }

    public long getDeviceExfiredTime() {
        return deviceExfiredTime;
    }

    public void setDeviceExfiredTime(long deviceExfiredTime) {
        this.deviceExfiredTime = deviceExfiredTime;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean checkStatus(String status){
        return this.deviceStatus.equals(status);
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceLocation='" + deviceLocation + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", deviceCommand=" + deviceCommand +
                ", deviceServices=" + deviceServices +
                ", deviceCreateTime='" + deviceCreateTime + '\'' +
                ", deviceExfiredTime='" + deviceExfiredTime + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}';
    }
}
