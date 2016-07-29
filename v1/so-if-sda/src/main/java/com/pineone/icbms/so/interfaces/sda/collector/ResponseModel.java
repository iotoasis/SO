package com.pineone.icbms.so.interfaces.sda.collector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Melvin on 2016. 1. 7..
 * Parsing from SDA Data.
 */
public class ResponseModel {

    /**
     * (List)Content Use  ExpectJsonData
     */
    private List<ExpectJsonData> content;

    public List<ExpectJsonData> getContent() {
        return content;
    }

    public void setContent(List<ExpectJsonData> content) {
        this.content = content;
    }


    public List<ExpectJsonData> addDevice(ExpectJsonData devices) {
        if(this.content == null)
            this.content = new ArrayList<>();
        content.add(devices);
        return content;
    }
}
