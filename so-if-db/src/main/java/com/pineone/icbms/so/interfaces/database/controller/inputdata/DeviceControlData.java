package com.pineone.icbms.so.interfaces.database.controller.inputdata;

/**
 * Created by melvin on 2017. 5. 15..
 */
public class DeviceControlData {

    private String deviceId;
    private String unit;
    private String value;
    private String maxValue;
    private String minValue;
    private String unitDescription;
    private String valueDescription;
    private String contextModelId;

    public DeviceControlData(String deviceId, String unit, String value, String contextModelId) {
        this.deviceId = deviceId;
        this.unit = unit;
        this.value = value;
        this.contextModelId = contextModelId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getValueDescription() {
        return valueDescription;
    }

    public void setValueDescription(String valueDescription) {
        this.valueDescription = valueDescription;
    }

    public String getContextModelId() {
        return contextModelId;
    }

    public void setContextModelId(String contextModelId) {
        this.contextModelId = contextModelId;
    }

    @Override
    public String toString() {
        return "DeviceControlData{" +
                "deviceId='" + deviceId + '\'' +
                ", unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", minValue='" + minValue + '\'' +
                ", unitDescription='" + unitDescription + '\'' +
                ", valueDescription='" + valueDescription + '\'' +
                ", contextModelId='" + contextModelId + '\'' +
                '}';
    }
}
