package com.pineone.icbms.so.iot.resources.device;

/**
 * Created by pahnj on 2016-01-04.
 */
public class InstalledDevice {

    /**
     * id
     */
    String id;
    /**
     * name
     */
    String name;
    /**
     * uri
     */
    String uri;
    /**
     * device Information ID
     */
    String deviceInformationID;

    public InstalledDevice(String id, String name, String uri, String deviceInfomationID) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.deviceInformationID = deviceInfomationID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDeviceInfomationID() {
        return deviceInformationID;
    }

    public void setDeviceInfomationID(String deviceInfomationID) {
        this.deviceInformationID = deviceInfomationID;
    }
}
