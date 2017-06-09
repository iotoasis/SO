package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.interfaces.database.model.key.DeviceControlPK;

import javax.persistence.*;

/**
 * Created by melvin on 2017. 5. 15..
 */
@Entity
@IdClass(DeviceControlPK.class)
@Table(name = "device_control")
public class DeviceControlForDB {
    //
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "unit")
    private String unit;

    @Column(name = "value")
    private String value;

    @Column(name = "max_value")
    private String maxValue;

    @Column(name = "min_value")
    private String minValue;

    @Column(name = "unit_description")
    private String unitDescription;

    @Column(name = "value_description")
    private String valueDescription;

    @Id
    @Column(name = "context_model_id")
    private String contextModelId;

    public DeviceControlForDB() {
    }

    public DeviceControlForDB(String id, String unit, String value, String contextModelId) {
        this.id = id;
        this.unit = unit;
        this.value = value;
        this.contextModelId = contextModelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "DeviceControlForDB{" +
                "id='" + id + '\'' +
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
