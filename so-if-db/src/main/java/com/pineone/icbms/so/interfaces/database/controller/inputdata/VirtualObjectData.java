package com.pineone.icbms.so.interfaces.database.controller.inputdata;

import java.util.Date;

/**
 * Created by melvin on 2017. 3. 27..
 */

//NOTE: 외부로부터 입력받기 위한 VirtualObject 데이터 셋 구성
public class VirtualObjectData {

    String name;
    String functionality_id;
    String aspect_id;
    String description;
    Date modified_date;


    public VirtualObjectData() {
    }

    public VirtualObjectData(String name, String functionality_id, String aspect_id, String description) {
        this.name = name;
        this.functionality_id = functionality_id;
        this.aspect_id = aspect_id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionality_id() {
        return functionality_id;
    }

    public void setFunctionality_id(String functionality_id) {
        this.functionality_id = functionality_id;
    }

    public String getAspect_id() {
        return aspect_id;
    }

    public void setAspect_id(String aspect_id) {
        this.aspect_id = aspect_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }
}
