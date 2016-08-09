package com.pineone.icbms.so.entity;

/**
 * Device Result model class for repository.<BR/>
 */
public class DeviceResult {

    /**
     * SI send Message (Json data)
     */
    String sendMessage;

    /**
     * Transmission confirmation results
     */
    String result1;

    /**
     * Control check results
     */
    String result2;

    /**
     *  Command Id
     */
    String commandId;

    /**
     * control value
     */
    String value;

    /**
     *  control deviceUrl
     */
    String deviceUrl;

    public DeviceResult() {
    }

    public DeviceResult(String sendMessage, String result1, String result2, String commandId, String value, String deviceUrl) {
        this.sendMessage = sendMessage;
        this.result1 = result1;
        this.result2 = result2;
        this.commandId = commandId;
        this.value = value;
        this.deviceUrl = deviceUrl;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public void setDeviceUrl(String deviceUrl) {
        this.deviceUrl = deviceUrl;
    }
}
