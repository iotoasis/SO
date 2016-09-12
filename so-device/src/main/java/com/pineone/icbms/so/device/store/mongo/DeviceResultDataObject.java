package com.pineone.icbms.so.device.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DeviceResult")
public class DeviceResultDataObject {

    String sendMessage;

    String result1;

    String result2;

    String commandId;

    String value;

    String deviceUrl;

    public DeviceResultDataObject() {
    }

    public DeviceResultDataObject(String sendMessage, String result1, String result2, String commandId, String value, String deviceUrl) {
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

    public String getResult1() {
        return result1;
    }

    public String getResult2() {
        return result2;
    }

    public String getCommandId() {
        return commandId;
    }

    public String getValue() {
        return value;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    @Override
    public String toString() {
        return "DeviceResultDataObject{" +
                "sendMessage='" + sendMessage + '\'' +
                ", result1='" + result1 + '\'' +
                ", result2='" + result2 + '\'' +
                ", commandId='" + commandId + '\'' +
                ", value='" + value + '\'' +
                ", deviceUrl='" + deviceUrl + '\'' +
                '}';
    }
}
