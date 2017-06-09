package com.pineone.icbms.so.devicecontrol.model.virtualdevice;

/**
 * device control value.<BR/>
 *
 * Created by uni4love on 2017. 1. 12..
 */
public class DeviceControlValue<V> {
    /**
     * control key
     */
    String key;

    /**
     * control value
     */
    V value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
